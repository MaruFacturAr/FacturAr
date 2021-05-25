package com.facturar.app.utils.ws.afip.authorization;

import com.facturar.app.utils.ws.afip.Utils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

public final class Auth {
    private final String token;
    private final String sign;
    private final String cuit;

    public Auth(String token, String sign, String cuit) {
        this.token = token;
        this.sign  = sign;
        this.cuit  = cuit;
    }

    public static String toXML(Auth auth) {
        return "<ar:Auth>" +
                "<ar:Token>" + auth.token() + "</ar:Token>" +
                "<ar:Sign>" + auth.sign() + "</ar:Sign>" +
                "<ar:Cuit>" + auth.cuit() + "</ar:Cuit>" +
                "</ar:Auth>";
    }

    public String token() {
        return token;
    }

    public String sign() {
        return sign;
    }

    public String cuit() {
        return cuit;
    }

    public boolean isProduction() throws IOException, SAXException, ParserConfigurationException, ParserConfigurationException, SAXException {
        byte[]   decodedBytes    = Base64.getDecoder().decode(token);
        String   decodedString   = new String(decodedBytes);
        var      document        = Utils.convertStringToXMLDocument(decodedString);
        NodeList nodeList        = document.getElementsByTagName("id");
        Element element         = (Element) nodeList.item(0);
        String   elementIdString = element.getAttribute("src").split(Pattern.quote(","))[0].replace("CN=", "");

        return !elementIdString.contains("wsaahomo");
    }
}
