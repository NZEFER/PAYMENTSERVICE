package ZEFER.PAYMENTSERVICE.Service.handler;

import ZEFER.PAYMENTSERVICE.Controller.kafka.producer.PaymentTransactionProducer;
import ZEFER.PAYMENTSERVICE.Model.Entity.BankAccount;
import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionCommand;
import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionStatus;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Service.BankAccountService;
import ZEFER.PAYMENTSERVICE.Service.PaymentTransactionService;
import ZEFER.PAYMENTSERVICE.Service.PaymentTransactionValidator;
import ZEFER.PAYMENTSERVICE.mapper.PaymentTransactionMapper;
import ZEFER.PAYMENTSERVICE.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePaymentTransactionHandler implements PaymentTransactionCommandHandler {
    private final JsonConverter jsonConverter;
    private final PaymentTransactionValidator paymentTransactionValidator;
    private final BankAccountService bankAccountService;
    private final PaymentTransactionMapper paymentTransactionMapper;
    private final PaymentTransactionService paymentTransactionService;
    private final PaymentTransactionProducer paymentTransactionProducer;


    @Override
    public void process(String requestId, String message) {
        var request = jsonConverter.fromJson(message, CreatePaymentTransactionRequest.class);
        paymentTransactionValidator.validateCreatePaymentTransactionRequest(request);

        var sourceBankAccount = bankAccountService.findById(request.getSourceBankAccountId()).get();
        sourceBankAccount.setBalance(
                sourceBankAccount.getBalance().subtract(request.getAmount())
        );

        Optional<BankAccount> destinationBankAccount = Optional.empty();
        if (request.getDestinationBackAccountId() != null) {
            destinationBankAccount = bankAccountService.findById(request.getDestinationBackAccountId());
            destinationBankAccount.get().setBalance(
                    destinationBankAccount.get().getBalance().add(request.getAmount())
            );
        }

        var entity = paymentTransactionMapper.toEntity(request);
        entity.setSoureBankAccount(sourceBankAccount);
        destinationBankAccount.ifPresent(entity::setDestinationBackAccount);
        entity.setPaymentTransactionStatus(PaymentTransactionStatus.SUCCESS);

        var savedEntity = paymentTransactionService.save(entity);

        if (destinationBankAccount.isPresent()) {
            bankAccountService.saveAll(List.of(sourceBankAccount, destinationBankAccount.get()));
        } else {
            bankAccountService.saveAll(List.of(sourceBankAccount));
        }

        paymentTransactionProducer.sentCommandResult(
                PaymentTransactionProducer.RESULT_TOPIC,
                requestId,
                jsonConverter.toJson(savedEntity),
                PaymentTransactionCommand.CREATE
        );

    }
}

