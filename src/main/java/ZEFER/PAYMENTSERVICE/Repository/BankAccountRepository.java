package ZEFER.PAYMENTSERVICE.Repository;

import ZEFER.PAYMENTSERVICE.Model.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
}
