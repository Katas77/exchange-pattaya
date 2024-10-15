package com.example.exchange_pattaya.getexchangerate;


import com.example.exchange_pattaya.exception.CbrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

@Component
public class ExchangeRateService {

    private static final String USD_XPATH = "/ValCurs//Valute[@ID='R01235']/Value";
    private static final String EUR_XPATH = "/ValCurs//Valute[@ID='R01239']/Value";
    private static final String CNY_XPATH = "/ValCurs//Valute[@ID='R01375']/Value";
    private static final String GBP_XPATH = "/ValCurs//Valute[@ID='R01035']/Value";
    private static final String GEL_XPATH = "/ValCurs//Valute[@ID='R01210']/Value";

    @Autowired
    private CbrClient client;


    public Double getUSDExchangeRate() throws CbrException {
        var xmlOptional = client.getXML();
        String xml = xmlOptional.orElseThrow(
                () -> new CbrException("Не удалось получить XML")
        );
        String nominalStr = extractCurrencyValueFromXML(xml, USD_XPATH).replaceAll(",", ".");
        return Double.valueOf((nominalStr));
    }


    public Double getEURExchangeRate() throws CbrException {
        var xmlOptional = client.getXML();
        String xml = xmlOptional.orElseThrow(
                () -> new CbrException("Не удалось получить XML")
        );
        String nominalStr = extractCurrencyValueFromXML(xml, EUR_XPATH).replaceAll(",", ".");
        return Double.valueOf((nominalStr));
    }

    public Double getCNYExchangeRate() throws CbrException {
        var xmlOptional = client.getXML();
        String xml = xmlOptional.orElseThrow(
                () -> new CbrException("Не удалось получить XML")
        );
        String nominalStr = extractCurrencyValueFromXML(xml, CNY_XPATH).replaceAll(",", ".");
        return Double.valueOf((nominalStr));
    }

    public Double getGBPExchangeRate() throws CbrException {
        var xmlOptional = client.getXML();
        String xml = xmlOptional.orElseThrow(
                () -> new CbrException("Не удалось получить XML")
        );
        String nominalStr = extractCurrencyValueFromXML(xml, GBP_XPATH).replaceAll(",", ".");
        return Double.valueOf((nominalStr));
    }

    public Double getGELExchangeRate() throws CbrException {
        var xmlOptional = client.getXML();
        String xml = xmlOptional.orElseThrow(
                () -> new CbrException("Не удалось получить XML")
        );
        String nominalStr = extractCurrencyValueFromXML(xml, GEL_XPATH).replaceAll(",", ".");
        return Double.valueOf((nominalStr));
    }



    private static String extractCurrencyValueFromXML(String xml, String xpathExpression)
            throws CbrException {
        System.out.println(xml);
        var source = new InputSource(new StringReader(xml));
        try {
            var xpath = XPathFactory.newInstance().newXPath();
            var document = (Document) xpath.evaluate("/", source, XPathConstants.NODE);

            return xpath.evaluate(xpathExpression, document);
        } catch (XPathExpressionException e) {
            throw new CbrException("Не удалось распарсить XML", e);
        }
    }
}