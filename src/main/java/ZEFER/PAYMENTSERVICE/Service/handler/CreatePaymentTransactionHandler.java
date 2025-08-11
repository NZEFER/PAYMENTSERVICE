package ZEFER.PAYMENTSERVICE.Service.handler;

import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Service.PaymentTransactionValidator;
import ZEFER.PAYMENTSERVICE.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePaymentTransactionHandler implements PaymentTransactionCommandHandler {
    private final JsonConverter jsonConverter;
    private final PaymentTransactionValidator paymentTransactionValidator;

    @Override
    public void process(String requestId, String message) {
        var request = jsonConverter.fromJson(message, CreatePaymentTransactionRequest.class);
        paymentTransactionValidator.validateCreatePaymentTransactionRequest(request);


    }
}

