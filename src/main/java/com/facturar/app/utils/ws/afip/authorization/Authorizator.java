package com.facturar.app.utils.ws.afip.authorization;
import com.facturar.app.utils.ws.afip.AfipService;
import com.facturar.app.utils.ws.afip.Utils;
import org.apache.axis.client.*;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.Base64;
import org.apache.axis.encoding.XMLType;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ParameterMode;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
public final class Authorizator {
    private final AfipService serviceEnun;
    private final DSTDN            dstdn;
    private final File             certificatePEM;
    private final File             keyPKCS;
    private final String           url;
    private final Optional<String> signer;
    private final Optional<String> password;

    public Authorizator(AfipService service, DSTDN dstdn, File certificatePEM, File keyPKCS, Optional<String> signer, Optional<String> password) {
        this.serviceEnun        = service;
        this.dstdn          = dstdn;
        this.certificatePEM = certificatePEM;
        this.keyPKCS        = keyPKCS;
        this.url            = service.value().contains("production") ? "https://wsaa.afip.gov.ar/ws/services/LoginCms" :
                "https://wsaahomo.afip.gov.ar/ws/services/LoginCms";
        this.signer         = signer;
        this.password       = password;
    }

    public Auth authenticate() {
        try {
            Path          authPathFile = null;
            LocalDateTime dateTimeNow  = LocalDateTime.now();

            File fileAtThisDay = authorizationResponseXMLFile(dateTimeNow, dateTimeNow.plusDays(1));
            File fileAtLastDay = authorizationResponseXMLFile(dateTimeNow, dateTimeNow.minusDays(1));

            if (fileAtThisDay.exists() && !fileAtLastDay.exists()) {
                authPathFile = Paths.get(fileAtThisDay.getPath());
            }

            if (!fileAtThisDay.exists() && fileAtLastDay.exists()) {
                authPathFile = Paths.get(fileAtLastDay.getPath());
            }

            if (!fileAtThisDay.exists() && !fileAtLastDay.exists() || authPathFile == null) {
                invoke();
                authPathFile = Paths.get(fileAtThisDay.getAbsolutePath());
            }

            String content = Files.readString(authPathFile, StandardCharsets.UTF_8);

            var document = Utils.convertStringToXMLDocument(content);

            String token       = document.getElementsByTagName("token").item(0).getTextContent();
            String sign        = document.getElementsByTagName("sign").item(0).getTextContent();
            String destination = document.getElementsByTagName("destination").item(0).getTextContent();
            String cuit        = destination.replace("SERIALNUMBER=CUIT ", "").substring(0, 10);

            return new Auth(token, sign, cuit);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private File authorizationResponseXMLFile(LocalDateTime dateFrom, LocalDateTime dateTo) {
        String dateFromFormat = dateFrom.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String dateToFormat   = dateTo.format(DateTimeFormatter.ISO_LOCAL_DATE);

        return new File(String.format("src/main/resources/authorization_response_%s_%s.xml", dateFromFormat, dateToFormat));
    }

    private void invoke() throws Exception {
        ensureFiles();

        Service service = new Service();
        Call    call    = (Call) service.createCall();

        call.setTargetEndpointAddress(new URL(url));
        call.setOperationName("loginCms");
        call.addParameter("request", XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);

        byte[] authorization = createCMS();

        String response = (String) call.invoke(new Object[]{Base64.encode(authorization)});

        saveAuthorizationResponseXML(response);
    }

    private void saveAuthorizationResponseXML(String xmlSource) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder        builder = factory.newDocumentBuilder();
        Document               doc     = builder.parse(new InputSource(new StringReader(xmlSource)));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer        transformer        = transformerFactory.newTransformer();
        DOMSource          source             = new DOMSource(doc);

        LocalDateTime dateTimeNow           = LocalDateTime.now();
        String        dateTimeNowFormat     = dateTimeNow.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String        dateTimePlusDayFormat = dateTimeNow.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);


        String xmlFile = String.format("authorization_response-%s-%s.xml", dateTimeNowFormat, dateTimePlusDayFormat);

        StreamResult result = new StreamResult(new File(xmlFile));
        transformer.transform(source, result);
    }

    private void ensureFiles() throws Exception {
        if (!this.keyPKCS.exists())
            throw new Exception("the private key not exists");

        if (!this.certificatePEM.exists())
            throw new Exception("the certificate PEM not exists");

    }

    private byte[] createCMS() throws Exception {
        char[] passwordArray = password.orElse("").toCharArray();
        KeyStore        keyStore  = KeyStore.getInstance("pkcs12");
        FileInputStream p12Stream = new FileInputStream(keyPKCS.getAbsolutePath());
        keyStore.load(p12Stream, passwordArray);
        p12Stream.close();
//signer.orElse("")
        PrivateKey      privateKey      = (PrivateKey) keyStore.getKey(signer.orElse(""), passwordArray);
        X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(signer.orElse(""));
        String          signerDN        = x509Certificate.getSubjectDN().toString();


        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        String loginTicketRequestXML = createLoginTicketRequestXML(signerDN);

        SignerInfoGenerator signerInfoGenerator = new JcaSimpleSignerInfoGeneratorBuilder().build("SHA256WithRSA", privateKey, x509Certificate);

        CMSSignedDataGenerator cmsSignedDataGenerator = new CMSSignedDataGenerator();
        cmsSignedDataGenerator.addSignerInfoGenerator(signerInfoGenerator);
        cmsSignedDataGenerator.addCertificate(new X509CertificateHolder(x509Certificate.getEncoded()));

        CMSTypedData  data       = new CMSProcessableByteArray(loginTicketRequestXML.getBytes());
        CMSSignedData signedData = cmsSignedDataGenerator.generate(data, true);

        return signedData.getEncoded();
    }

    private String createLoginTicketRequestXML(String signerDN) {
        LocalDateTime date    = LocalDateTime.now();
        LocalDateTime expDate = date.plusDays(1);
        String        id      = String.valueOf(new Date().getTime() / 1000);

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder        documentBuilder        = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            Element root = document.createElement("loginTicketRequest");
            document.appendChild(root);

            Element header  = document.createElement("header");
            Element service = document.createElement("service");
            service.appendChild(document.createTextNode(this.serviceEnun.value()));

            root.appendChild(header);
            root.appendChild(service);

            if (signer.isPresent()) {
                Element source = document.createElement("source");
                source.appendChild(document.createTextNode(signerDN));
            }

            Element destination = document.createElement("destination");
            destination.appendChild(document.createTextNode(dstdn.dstdn()));

            Element uniqueId = document.createElement("uniqueId");
            uniqueId.appendChild(document.createTextNode(id));

            Element generationTime = document.createElement("generationTime");
            generationTime.appendChild(document.createTextNode(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

            Element expirationTime = document.createElement("expirationTime");
            expirationTime.appendChild(document.createTextNode(expDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

            header.appendChild(uniqueId);
            header.appendChild(generationTime);
            header.appendChild(expirationTime);

            TransformerFactory tf          = TransformerFactory.newInstance();
            Transformer        transformer = tf.newTransformer();

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.getBuffer().toString();

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}