package com.acme.elegant.repository;

import com.acme.elegant.model.PaymentAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Long>{
    //Page<PaymentAccount> findByUserId(Long userId, Pageable pageable);
    //Optional<PaymentAccount> findByIdAndUserId(Long id, Long userId);
}
