package ZEFER.PAYMENTSERVICE.config;

import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionCommand;
import ZEFER.PAYMENTSERVICE.Service.handler.CreatePaymentTransactionHandler;
import ZEFER.PAYMENTSERVICE.Service.handler.PaymentTransactionCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaymentTransactionCommandConfig {
    @Bean
    public Map<PaymentTransactionCommand, PaymentTransactionCommandHandler> commandHandlers(
            CreatePaymentTransactionHandler createPaymentTransactionHandler,
            CreatePaymentTransactionHandler cancelPaymentTransactionHandler
    ) {
        Map<PaymentTransactionCommand, PaymentTransactionCommandHandler> commandHandlers = new HashMap<>();
        commandHandlers.put(PaymentTransactionCommand.CREATE, createPaymentTransactionHandler);
        commandHandlers.put(PaymentTransactionCommand.REFUND, cancelPaymentTransactionHandler);
        return commandHandlers;
    }
}
