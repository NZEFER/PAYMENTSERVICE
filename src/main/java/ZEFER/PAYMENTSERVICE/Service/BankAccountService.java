package ZEFER.PAYMENTSERVICE.Service;

import ZEFER.PAYMENTSERVICE.Model.Entity.BankAccount;
import ZEFER.PAYMENTSERVICE.Repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public Optional<BankAccount> findById(long id) {
        return bankAccountRepository.findById(id);
    }

    @Transactional
    public List<BankAccount> saveAll(List<BankAccount> bankAccounts) {
        return bankAccountRepository.saveAll(bankAccounts);
    }
}

