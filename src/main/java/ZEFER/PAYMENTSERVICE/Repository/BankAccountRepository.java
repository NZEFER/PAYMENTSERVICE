package ZEFER.PAYMENTSERVICE.Repository;

import ZEFER.PAYMENTSERVICE.Model.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
}
