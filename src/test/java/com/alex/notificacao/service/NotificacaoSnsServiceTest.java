package com.alex.notificacao.service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class NotificacaoSnsServiceTest {

    @Mock // Cria um mock (objeto falso) para o SnsClient
    private SnsClient snsClient;

    @InjectMocks // Cria uma instância real do serviço e injeta os mocks (o snsClient falso) nele
    private NotificacaoSnsService notificacaoSnsService;

    @Test
    @DisplayName("Deve enviar notificação com sucesso usando o mock do SNS")
    void deveEnviarNotificacaoComSucesso() {

        String telefone = "+5521999998888";
        String mensagem = "Prezado %s, sua proposta foi recebida. Breve retornaremos o contato";

        PublishResponse mockResponse = PublishResponse.builder()
                .messageId("mock-message-id-12345")
                .build();

        when(snsClient.publish(any(PublishRequest.class))).thenReturn(mockResponse);
        notificacaoSnsService.notificar(telefone, mensagem);
        verify(snsClient, times(1)).publish(any(PublishRequest.class));
    }
}