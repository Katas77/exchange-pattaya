package com.example.exchange_pattaya.configuration;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationOkHttpClient {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}