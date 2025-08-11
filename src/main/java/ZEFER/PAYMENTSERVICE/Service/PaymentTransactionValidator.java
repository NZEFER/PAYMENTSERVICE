package ZEFER.PAYMENTSERVICE.Service;

import ZEFER.PAYMENTSERVICE.Model.Entity.BankAccount;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.errors.PaymentTransactionValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentTransactionValidator {
    private final Validator validator;
    private final BankAccountService bankAccountService;

    public void validateCreatePaymentTransactionRequest(CreatePaymentTransactionRequest request) {
        var violations = validator.validate(request);
        List<String> errors = new ArrayList<>(
                violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .toList()
        );

        Optional<BankAccount> sourceBank = Optional.empty();
        if (request.getSourceBankAccountId() != null) {
            sourceBank = bankAccountService.findById(request.getSourceBankAccountId());
            if (sourceBank.isEmpty()) {
                errors.add("Source bank account not found, source account id: " + request.getSourceBankAccountId());
            }
        }

        if (request.getDestinationBackAccountId() != null) {
            var sourceBankAccount = bankAccountService.findById(request.getDestinationBackAccountId());
            if (sourceBankAccount.isEmpty()) {
                errors.add("Destination bank account not found, source account id: " + request.getSourceBankAccountId());
            }
        }

        if (request.getAmount() != null && sourceBank.isPresent()) {
            if (sourceBank.get().getBalance().compareTo(request.getAmount()) < 0)
                errors.add("Source bank account balance less than requested account, source account id: " + request.getSourceBankAccountId());
        }

        if (!errors.isEmpty()) {
            throw new PaymentTransactionValidationException(errors);
        }
    }
}
