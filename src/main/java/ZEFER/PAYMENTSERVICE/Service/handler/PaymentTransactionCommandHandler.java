package ZEFER.PAYMENTSERVICE.Service.handler;

import org.apache.kafka.common.protocol.types.Field;

public interface PaymentTransactionCommandHandler {
    void process(Long requestId, String message);
}
