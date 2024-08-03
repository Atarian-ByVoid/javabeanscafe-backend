package com.javabeanscafe.infrastructure.adapter.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabeanscafe.infrastructure.adapter.dto.TransacaoDTO;

@Service
public class PagamentoRequestProducer {

    @Value("${topicos.pagamento.request.topic}")
    private String pagamentoRequestTopic;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(TransacaoDTO pagamento) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(pagamento);
        kafkaTemplate.send(pagamentoRequestTopic, orderAsMessage);
        return "Pagamento enviado";
    }
}