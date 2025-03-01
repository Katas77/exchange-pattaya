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
        return getExchangeRate(USD_XPATH);
    }

    public Double getEURExchangeRate() throws CbrException {
        return getExchangeRate(EUR_XPATH);
    }

    public Double getCNYExchangeRate() throws CbrException {
        return getExchangeRate(CNY_XPATH);
    }

    public Double getGBPExchangeRate() throws CbrException {
        return getExchangeRate(GBP_XPATH);
    }

    public Double getGELExchangeRate() throws CbrException {
        return getExchangeRate(GEL_XPATH);
    }

    private Double getExchangeRate(String xpathExpression) throws CbrException {
        String xml = client.getXML()
                .orElseThrow(() -> new CbrException("Не удалось получить XML"));

        String nominalStr = extractCurrencyValueFromXML(xml, xpathExpression)
                .replace(",", ".");
        return Double.valueOf(nominalStr);
    }

    private static String extractCurrencyValueFromXML(String xml, String xpathExpression) throws CbrException {
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
