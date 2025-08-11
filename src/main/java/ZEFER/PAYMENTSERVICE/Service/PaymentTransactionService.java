package ZEFER.PAYMENTSERVICE.Service;

import ZEFER.PAYMENTSERVICE.Model.Entity.PaymentTransaction;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionResponse;
import ZEFER.PAYMENTSERVICE.Repository.PaymentTransactionRepository;
import ZEFER.PAYMENTSERVICE.mapper.PaymentTransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final PaymentTransactionMapper paymentTransactionMapper;

    public PaymentTransaction save(PaymentTransaction paymentTransaction) {
        return paymentTransactionRepository.save(paymentTransaction);
    }
}
