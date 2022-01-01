package com.example.bookstore.model.order.payment;


import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "payments")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "created_date")
//    @CreatedDate
    private Date createdDate;

    @Column(name = "updated_date")
//    @LastModifiedDate
    private Date updatedDate;

    @OneToOne
//    @JsonIgnoreProperties
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
//    @JsonIgnoreProperties
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

//    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Check check;
//
//    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Cash cash;
//
//    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Credit credit;
}
