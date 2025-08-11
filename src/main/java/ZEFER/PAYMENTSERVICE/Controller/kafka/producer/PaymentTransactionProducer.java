package ZEFER.PAYMENTSERVICE.Controller.kafka.producer;

import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentTransactionProducer {
    public final static String RESULT_TOPIC = "payment-command-result";

    private final static String PAYMENT_TRANSACTION_COMMAND_TYPE_HEADER = "command";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sentCommandResult(String topic, String requestId, String message, PaymentTransactionCommand command){
        var kafkaMessage = buildMessage(topic, requestId, message, command);
        kafkaTemplate.send(kafkaMessage);
        log.info("Successfully sent command result: {}",kafkaMessage);
    }

    private Message<String> buildMessage(String topic, String requestId, String message, PaymentTransactionCommand command){
        return MessageBuilder.withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY, requestId)
                .setHeader(PAYMENT_TRANSACTION_COMMAND_TYPE_HEADER, command.toString())
                .build();
    }

}
