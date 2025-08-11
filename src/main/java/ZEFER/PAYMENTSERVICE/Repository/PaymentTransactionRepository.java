package ZEFER.PAYMENTSERVICE.Repository;

import ZEFER.PAYMENTSERVICE.Model.Entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
}
