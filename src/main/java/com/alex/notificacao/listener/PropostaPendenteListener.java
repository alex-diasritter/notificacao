package com.alex.notificacao.listener;
import com.alex.notificacao.constante.MsgConstante;
import com.alex.notificacao.domain.Proposta;
import com.alex.notificacao.service.NotificacaoSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    @Autowired
    private NotificacaoSnsService snsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String msg = String.format(MsgConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario());
        snsService.notificar(proposta.getUsuario().getTelefone(),msg);
    }


}