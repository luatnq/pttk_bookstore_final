package com.example.bookstore.model.customer;

import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

//@Entity
//@Data
//@Table(name = "customer_news")
//@AllArgsConstructor
//@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer_id")
public class CustomerNew extends Customer implements Serializable {

    @Column( name = "note")
    private String note;

}
