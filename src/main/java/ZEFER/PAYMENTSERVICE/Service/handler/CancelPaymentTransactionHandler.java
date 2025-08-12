package ZEFER.PAYMENTSERVICE.Service.handler;

import ZEFER.PAYMENTSERVICE.Controller.kafka.producer.PaymentTransactionProducer;
import ZEFER.PAYMENTSERVICE.Model.Entity.PaymentTransaction;
import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionCommand;
import ZEFER.PAYMENTSERVICE.Model.dto.CancelPaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Service.BankAccountService;
import ZEFER.PAYMENTSERVICE.Service.PaymentTransactionService;
import ZEFER.PAYMENTSERVICE.Service.PaymentTransactionValidator;
import ZEFER.PAYMENTSERVICE.Service.RefundService;
import ZEFER.PAYMENTSERVICE.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CancelPaymentTransactionHandler implements PaymentTransactionCommandHandler {
    private final PaymentTransactionProducer paymentTransactionProducer;
    private JsonConverter jsonConverter;
    private final PaymentTransactionValidator paymentTransactionValidator;
    private final PaymentTransactionService paymentTransactionService;
    private final BankAccountService bankAccountService;
    private final RefundService refundService;

    @Override
    public void process(String requestId, String message) {
        var request = jsonConverter.fromJson(message, CancelPaymentTransactionRequest.class);
        paymentTransactionValidator.validateCancelPaymentTransactionRequest(request);

        var sourceTransaction = paymentTransactionService.findById(request.getTransactionId()).get();
        var sourceBankAccount = sourceTransaction.getSoureBankAccount();
        sourceBankAccount.setBalance(
                sourceBankAccount.getBalance().add(request.getRefundedAmount())
        );

        if (sourceTransaction.getDestinationBackAccount() != null) {
            var destinationBankAccount = sourceTransaction.getDestinationBackAccount();
            sourceBankAccount.setBalance(
                    destinationBankAccount.getBalance().subtract(request.getRefundedAmount())
            );
        }
        var response = refundService.createRefund(request, sourceTransaction);

        paymentTransactionProducer.sentCommandResult(
                PaymentTransactionProducer.RESULT_TOPIC,
                requestId,
                jsonConverter.toJson(response),
                PaymentTransactionCommand.REFUND
        );

    }
}
