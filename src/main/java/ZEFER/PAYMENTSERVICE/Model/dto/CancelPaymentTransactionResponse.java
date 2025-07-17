package ZEFER.PAYMENTSERVICE.Model.dto;

import ZEFER.PAYMENTSERVICE.Model.dto.enums.CommandResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPaymentTransactionResponse {
    private Long refundId;
    private CommandResultStatus status;
    private String errorMessage;
}
