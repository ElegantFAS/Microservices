package com.acme.elegant.service;

import com.acme.elegant.exception.ResourceNotFoundException;
import com.acme.elegant.model.PaymentAccount;
import com.acme.elegant.repository.PaymentAccountRepository;
import com.acme.elegant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentAccountServiceImpl implements PaymentAccountService{

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PaymentAccount createPaymentAccount(Long userId, PaymentAccount paymentAccount) {

        return userRepository.findById(userId).map(user ->{
            paymentAccount.setUser(user);
            return paymentAccountRepository.save(paymentAccount);
        }).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));

    }

    @Override
    public PaymentAccount updatePaymentAccount(Long paymentAccountId, PaymentAccount paymentAccountRequest) {
        PaymentAccount paymentAccount = getPaymentAccountById(paymentAccountId);
        paymentAccount.setCardNumber(paymentAccountRequest.getCardNumber());
        paymentAccount.setExpirationDate(paymentAccountRequest.getExpirationDate());
        paymentAccount.setAddress(paymentAccountRequest.getAddress());
        paymentAccount.setOwnerName(paymentAccountRequest.getOwnerName());
        paymentAccount.setSecurityCode(paymentAccountRequest.getSecurityCode());
        return paymentAccountRepository.save(paymentAccount);
    }

    @Override
    public ResponseEntity<?> deletePaymentAccount(Long paymentAccountId) {
        PaymentAccount paymentAccount = getPaymentAccountById(paymentAccountId);
        paymentAccountRepository.delete(paymentAccount);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<PaymentAccount> getAllPaymentAccounts(Pageable pageable) {
        return paymentAccountRepository.findAll(pageable);
    }

    @Override
    public PaymentAccount getPaymentAccountById(Long paymentAccountId) {
        return paymentAccountRepository.findById(paymentAccountId)
                .orElseThrow(()->new ResourceNotFoundException("PaymentAccount","Id",paymentAccountId));
    }
}
