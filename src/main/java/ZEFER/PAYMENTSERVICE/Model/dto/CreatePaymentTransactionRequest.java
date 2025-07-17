package ZEFER.PAYMENTSERVICE.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentTransactionRequest {
    @NotNull
    private Long sourceBankAccountId;
    private Long destinationBackAccountId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String currency;
    private String description;
}
