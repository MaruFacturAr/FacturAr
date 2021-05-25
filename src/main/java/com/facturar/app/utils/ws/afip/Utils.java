package com.facturar.app.utils.ws.afip;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {
    public static String parsetoAfipDate(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public static boolean validateIfExistsAuthorizationResponseXMLFile() {
        LocalDateTime dateTimeNow            = LocalDateTime.now();
        String        dateTimeNowFormat      = dateTimeNow.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String        dateTimeMinusDayFormat = dateTimeNow.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        String        dateTimePlusDayFormat  = dateTimeNow.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);

        File fileGeneratedAtThisDay = new File(String.format("authorization_response-%s-%s.xml", dateTimeNowFormat, dateTimePlusDayFormat));
        File fileGeneratedAtLastDay = new File(String.format("authorization_response-%s-%s.xml", dateTimeMinusDayFormat, dateTimeNowFormat));

        return fileGeneratedAtThisDay.exists() || fileGeneratedAtLastDay.exists();
    }

    public static Document convertStringToXMLDocument(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new InputSource(new StringReader(xmlString)));
    }
}
