package com.example.bookstore.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Data
//@Table(name = "customer_members")
//@AllArgsConstructor
//@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer_id")
public class CustomerMember extends Customer implements Serializable {

    @Column(name = "card_number")
    private String cardNumber;

}
