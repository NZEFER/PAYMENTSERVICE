package ZEFER.PAYMENTSERVICE.Repository;

import ZEFER.PAYMENTSERVICE.Model.Entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
}
