package com.alex.notificacao.service;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class NotificacaoSnsService {

    private static final Logger log = LoggerFactory.getLogger(NotificacaoSnsService.class);
    private final SnsClient snsClient;

    public NotificacaoSnsService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void notificar(String telefone, String msg) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .phoneNumber(telefone)
                    .message(msg)
                    .build();

            PublishResponse result = snsClient.publish(request);

            log.info("Notificação enviada com sucesso. Message ID: {}", result.messageId());

        } catch (SnsException e) {
            log.error("Erro ao enviar a notificação SNS: {}", e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            log.error("Ocorreu um erro inesperado.", e);
        }
    }
}
