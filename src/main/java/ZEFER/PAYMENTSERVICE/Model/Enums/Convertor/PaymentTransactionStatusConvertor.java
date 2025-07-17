package ZEFER.PAYMENTSERVICE.Model.Enums.Convertor;

import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentTransactionStatusConvertor implements AttributeConverter<PaymentTransactionStatus,String> {

    @Override
    public String convertToDatabaseColumn(PaymentTransactionStatus paymentTransactionStatus) {
        return paymentTransactionStatus == null ? null : paymentTransactionStatus.name();
    }

    @Override
    public PaymentTransactionStatus convertToEntityAttribute(String s) {
        return s == null ? null : PaymentTransactionStatus.fromString(s);
    }
}
