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
@Table(name = "cashs")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "payment_id")
public class Cash extends Payment implements Serializable {
//    @Id
//    @Column(name = "payment_id")
//    private int id;

    @Column(name = "cash_tendered")
    private float cashTendered;

    public Cash(Order order, Shipment shipment, float amount){
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
        this.setOrder(order);
        this.setAmount(amount);
        this.setShipment(shipment);
    }
}
