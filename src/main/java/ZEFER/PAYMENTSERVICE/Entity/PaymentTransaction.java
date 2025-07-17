package ZEFER.PAYMENTSERVICE.Entity;

import ZEFER.PAYMENTSERVICE.Entity.Enums.PaymentTransactionStatus;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction extends BaseEntity{
    private PaymentTransactionStatus paymentTransactionStatus;
}
