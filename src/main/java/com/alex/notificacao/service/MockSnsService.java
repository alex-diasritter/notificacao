package com.alex.notificacao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Profile("dev") // Só será carregado no perfil "dev"
public class MockSnsService {

    private static final Logger logger = LoggerFactory.getLogger(MockSnsService.class);

    public String sendSms(String phoneNumber, String message) {

        // Simula o ID da mensagem (como o SNS faria)
        String fakeMessageId = UUID.randomUUID().toString();

        logger.info("\n--- MOCK SNS ---\n" +
                "SMS enviado para: {}\n" +
                "Conteúdo: {}\n" +
                "MessageId simulado: {}\n" +
                "---------------", phoneNumber, message, fakeMessageId);

        return fakeMessageId;
    }
}