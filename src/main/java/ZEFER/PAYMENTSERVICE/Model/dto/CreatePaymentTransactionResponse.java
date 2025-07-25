package ZEFER.PAYMENTSERVICE.Model.dto;

import ZEFER.PAYMENTSERVICE.Model.dto.enums.CommandResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentTransactionResponse {
    private Long paymentTransactionId;
    private CommandResultStatus status;
    private String errorMessage;
    private LocalDateTime executedAt;
}
