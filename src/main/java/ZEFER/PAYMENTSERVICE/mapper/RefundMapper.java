package ZEFER.PAYMENTSERVICE.mapper;

import ZEFER.PAYMENTSERVICE.Model.Entity.Refund;
import ZEFER.PAYMENTSERVICE.Model.dto.CancelPaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Model.dto.CancelPaymentTransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefundMapper {
    Refund toEntity(CancelPaymentTransactionRequest request);
    CancelPaymentTransactionResponse toResponse(Refund refund);
}
