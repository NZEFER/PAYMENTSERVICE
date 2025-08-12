package ZEFER.PAYMENTSERVICE.Service;

import ZEFER.PAYMENTSERVICE.Model.Entity.PaymentTransaction;
import ZEFER.PAYMENTSERVICE.Model.Enums.RefundStatus;
import ZEFER.PAYMENTSERVICE.Model.dto.CancelPaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Model.dto.CancelPaymentTransactionResponse;
import ZEFER.PAYMENTSERVICE.Repository.RefundRepository;
import ZEFER.PAYMENTSERVICE.mapper.RefundMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefundService {
    private final RefundMapper refundMapper;
    private final PaymentTransactionService paymentTransactionService;
    private final BankAccountService bankAccountService;
    private final RefundRepository refundRepository;


    public CancelPaymentTransactionResponse createRefund(CancelPaymentTransactionRequest request,
                                                         PaymentTransaction paymentTransaction){
        var entity = refundMapper.toEntity(request);
        entity.setPaymentTransaction(paymentTransaction);
        entity.setStatus(RefundStatus.COMPLETED);

        var savedEntity = refundRepository.save(entity);

        savedEntity.setPaymentTransaction(paymentTransaction);
        return refundMapper.toResponse(savedEntity);
    }
}
