package ZEFER.PAYMENTSERVICE.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPaymentTransactionRequest {
    @NotNull(message = "Transaction ID must not be null")
    private Long transactionId;
    @NotNull
    @Min(value = 1, message = "Refunded amount must not be null")
    private BigDecimal refundedAmount;
    private Field.Str reasons;

}
