package ZEFER.PAYMENTSERVICE.Model.Entity;

import ZEFER.PAYMENTSERVICE.Model.Enums.Convertor.RefundStatusConvertor;
import ZEFER.PAYMENTSERVICE.Model.Enums.RefundStatus;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.mapping.Join;

import java.math.BigDecimal;

public class Refund extends BaseEntity{
    private BigDecimal refundAmount;
    private String reason;

    @Convert(converter = RefundStatusConvertor.class)
    private RefundStatus status;

    @ManyToOne
    @JoinColumn(name = "paymentTransactionId", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;
}
