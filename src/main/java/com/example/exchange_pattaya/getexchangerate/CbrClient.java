package com.example.exchange_pattaya.getexchangerate;

import com.example.exchange_pattaya.exception.CbrException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;

@Component
public class CbrClient {

    @Autowired
    private OkHttpClient client;

    @Value("${cbr.currency.xml.url}")
    private String cbrCurrencyRatesXmlUrl;

    public Optional<String> getXML() throws CbrException {
        var request = new Request.Builder()
                .url(cbrCurrencyRatesXmlUrl)
                .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            return body == null ? Optional.empty() : Optional.of(body.string());
        } catch (IOException e) {
            throw new CbrException("Ошибка получения курсов валют от ЦБ РФ", e);
        }
    }
}