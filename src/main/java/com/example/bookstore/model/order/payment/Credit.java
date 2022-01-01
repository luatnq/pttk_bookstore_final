package com.example.bookstore.model.order.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "credit")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "payment_id")
public class Credit extends Payment implements Serializable {
//    @Id
//    @Column(name = "payment_id")
//    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    private String type;

    @Column(name = "exp_date")
    private Date exp_date;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "payment_id")
//    private Payment payment;
}
