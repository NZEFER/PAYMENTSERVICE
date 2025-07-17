package ZEFER.PAYMENTSERVICE.Model.Entity;

import ZEFER.PAYMENTSERVICE.Model.Enums.Convertor.PaymentTransactionStatusConvertor;
import ZEFER.PAYMENTSERVICE.Model.Enums.PaymentTransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction extends BaseEntity{
    private String currency;
    private BigDecimal amount;

    @Convert(converter = PaymentTransactionStatusConvertor.class)
    private PaymentTransactionStatus paymentTransactionStatus;

    private String errorMessage;

    @ManyToOne
    @JoinColumn(name = "sourceBankAccount")
    private BankAccount soureBankAccount;
    @ManyToOne
    @JoinColumn(name = "destinationBackAccount")
    private BankAccount destinationBackAccount;

    @OneToMany(mappedBy = "paymentTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Refund> refunds;
}
