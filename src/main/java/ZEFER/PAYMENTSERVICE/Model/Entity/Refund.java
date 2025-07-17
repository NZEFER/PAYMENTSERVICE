package ZEFER.PAYMENTSERVICE.Model.Entity;

import ZEFER.PAYMENTSERVICE.Model.Enums.Convertor.RefundStatusConvertor;
import ZEFER.PAYMENTSERVICE.Model.Enums.RefundStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Refund extends BaseEntity{
    private BigDecimal refundAmount;
    private String reason;

    @Convert(converter = RefundStatusConvertor.class)
    private RefundStatus status;

    @ManyToOne
    @JoinColumn(name = "paymentTransactionId", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;
}
