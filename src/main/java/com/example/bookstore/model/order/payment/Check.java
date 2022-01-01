package com.example.bookstore.model.order.payment;

import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "checks")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "payment_id")
public class Check extends Payment implements Serializable {
//    @Id
//    @Column(name = "payment_id")
//    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "bank_id")
    private String bankId;

    public Check(Order order, float amount, Shipment shipment, String name, String bankId){
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
        this.setOrder(order);
        this.setAmount(amount);
        this.setShipment(shipment);
        this.name = name;
        this.bankId = bankId;
    }
}
