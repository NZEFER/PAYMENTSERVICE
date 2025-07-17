package ZEFER.PAYMENTSERVICE.Entity;

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
public class BankAccount extends BaseEntity {
    private String number;

    private BigDecimal balance;
    private Long customerId;
    private String currency;
}
