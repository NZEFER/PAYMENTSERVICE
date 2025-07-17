package ZEFER.PAYMENTSERVICE.Model.dto.enums;

import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionCommand;
import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionStatus;
import lombok.Getter;

@Getter
public enum CommandResultStatus {
    SUCCESS,
    FAILED;
    public static CommandResultStatus fromString(String status){
        for (CommandResultStatus commandResultStatus : CommandResultStatus.values()) {
            if (commandResultStatus.name().equalsIgnoreCase(status)) {
                return commandResultStatus;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + status);
    }

}
