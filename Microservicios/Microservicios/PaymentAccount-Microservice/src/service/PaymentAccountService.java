package com.acme.elegant.service;

import com.acme.elegant.model.PaymentAccount;
import com.acme.elegant.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentAccountService {

    PaymentAccount createPaymentAccount(Long userId, PaymentAccount paymentAccount);
    PaymentAccount updatePaymentAccount(Long postId, PaymentAccount paymentAccountRequest);
    ResponseEntity<?> deletePaymentAccount(Long postId);
    Page<PaymentAccount> getAllPaymentAccounts(Pageable pageable);
    PaymentAccount getPaymentAccountById(Long paymentAccountId);
}
