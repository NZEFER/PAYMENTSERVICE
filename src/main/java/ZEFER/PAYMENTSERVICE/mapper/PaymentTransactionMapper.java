package ZEFER.PAYMENTSERVICE.mapper;

import ZEFER.PAYMENTSERVICE.Model.Entity.PaymentTransaction;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionRequest;
import ZEFER.PAYMENTSERVICE.Model.dto.CreatePaymentTransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTransactionMapper {
    PaymentTransaction toEntity(CreatePaymentTransactionRequest request);
    CreatePaymentTransactionResponse toResponse(PaymentTransaction response);
}
