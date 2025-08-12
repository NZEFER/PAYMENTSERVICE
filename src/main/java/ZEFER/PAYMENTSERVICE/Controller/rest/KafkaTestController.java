package ZEFER.PAYMENTSERVICE.Controller.rest;


import ZEFER.PAYMENTSERVICE.Controller.kafka.producer.PaymentTransactionProducer;
import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionCommand;
import ZEFER.PAYMENTSERVICE.Model.dto.CancelPaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@RestController
public class KafkaTestController {
    private final PaymentTransactionProducer paymentTransactionProducer;
    private final JsonConverter jsonConverter;

    @PostMapping("/create-payment")
    public String createPaymentTest() {
        var requestId = "request-Id";

        CreatePaymentTransactionRequest request = new CreatePaymentTransactionRequest(
                1L,
                2L,
                new BigDecimal("200.00"),
                "USD",
                "Test payment"
        );

        paymentTransactionProducer.sentCommandResult(PaymentTransactionProducer.COMMAND_TOPIC,
                requestId, jsonConverter.toJson(request), PaymentTransactionCommand.CREATE);

        return "Create payment command sent with request Id: " + requestId;
    }

    @PostMapping("/cancel-payment")
    public String cancelPaymentTest() {
        var requestId = "request-Id";

        CancelPaymentTransactionRequest request = new CancelPaymentTransactionRequest(
                1L,
                new BigDecimal("150.00"),
                "Test refund"
        );

        paymentTransactionProducer.sentCommandResult(PaymentTransactionProducer.COMMAND_TOPIC,
                requestId, jsonConverter.toJson(request), PaymentTransactionCommand.REFUND);

        return "Cancel payment command sent with request Id: " + requestId;
    }
}
