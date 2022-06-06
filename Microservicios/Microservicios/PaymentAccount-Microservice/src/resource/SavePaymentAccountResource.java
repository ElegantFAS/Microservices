package com.acme.elegant.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SavePaymentAccountResource {

    private String cardNumber;
    private String securityCode;
    private Date expirationDate;
    private String address;
    private String ownerName;
}
