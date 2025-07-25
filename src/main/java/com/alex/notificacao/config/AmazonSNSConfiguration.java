package com.alex.notificacao.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSNSConfiguration {

    @Value("${aws.acessKey}")
    private String acessKey;
    @Value("${aws.secretKey}")
    private String secretKey;


}
