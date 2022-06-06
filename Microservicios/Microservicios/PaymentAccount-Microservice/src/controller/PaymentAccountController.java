package com.acme.elegant.controller;

import com.acme.elegant.model.PaymentAccount;
import com.acme.elegant.resource.PaymentAccountResource;
import com.acme.elegant.resource.SavePaymentAccountResource;
import com.acme.elegant.service.PaymentAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="paymentAccounts")
@RestController
@RequestMapping("/api")
public class PaymentAccountController {

    private PaymentAccount convertToEntity(SavePaymentAccountResource resource){
        return mapper.map(resource, PaymentAccount.class);
    }
    private PaymentAccountResource convertToResource(PaymentAccount entity){
        return mapper.map(entity, PaymentAccountResource.class);
    }
    @Autowired
    private PaymentAccountService paymentAccountService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/users/{userId}/paymentAccount")
    public PaymentAccountResource createPaymentAccount(@PathVariable(name = "userId") Long userId,
                                                       @Valid @RequestBody SavePaymentAccountResource resource) {
        return convertToResource(paymentAccountService.createPaymentAccount(userId,
                convertToEntity(resource)));
    }

    @GetMapping("/paymentAccounts")
    public Page<PaymentAccountResource> getAllPaymentAccounts(Pageable pageable) {
        Page<PaymentAccount> paymentAccounts = paymentAccountService.getAllPaymentAccounts(pageable);
        List<PaymentAccountResource> resources = paymentAccounts.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/paymentAccounts/{paymentAccountId}")
    public PaymentAccountResource getPaymentAccountById(@PathVariable(name = "paymentAccountId") Long paymentAccountId) {
        return convertToResource(paymentAccountService.getPaymentAccountById(paymentAccountId));
    }

    @PutMapping("/paymentAccounts/{paymentAccountId}")
    public PaymentAccountResource updatePaymentAccount(@PathVariable(name = "paymentAccountId") Long paymentAccountId,
                                   @Valid @RequestBody SavePaymentAccountResource resource) {
        return convertToResource(paymentAccountService.updatePaymentAccount(paymentAccountId, convertToEntity(resource)));
    }

    @DeleteMapping("/paymentAccounts/{paymentAccountId}")
    public ResponseEntity<?> deletePaymentAccount(@PathVariable(name = "paymentAccountId") Long paymentAccountId) {
        return paymentAccountService.deletePaymentAccount(paymentAccountId);
    }
}
