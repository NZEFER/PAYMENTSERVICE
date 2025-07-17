package ZEFER.PAYMENTSERVICE.Model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreatePaymentTransactionRequest {
    @NonNull
    private Long sourceBankAccountId;
    private Long destinationBackAccountId;
}
