package com.acme.elegant.resource;

import com.acme.elegant.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class PaymentAccountResource {
    private Long id;
    private String cardNumber;
    private String securityCode;
    private Date expirationDate;
    private String address;
    private String ownerName;
    private Long userId;
}
