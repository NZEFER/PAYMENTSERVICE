package ZEFER.PAYMENTSERVICE.Model.Enums.Convertor;

import ZEFER.PAYMENTSERVICE.Model.Enums.RefundStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RefundStatusConvertor implements AttributeConverter<RefundStatus, String> {

    @Override
    public String convertToDatabaseColumn(RefundStatus refundStatus) {
        return refundStatus == null ? null : refundStatus.name();
    }

    @Override
    public RefundStatus convertToEntityAttribute(String s) {
        return s == null ? null : RefundStatus.fromString(s);
    }
}
