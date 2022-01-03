package com.example.bookstore.model.order;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "shipments")
@AllArgsConstructor
@NoArgsConstructor
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "code_ship")
    private String codeShip;

    @Column(name = "address")
    private String address;

    @Column(name = "created_date")
//    @CreatedDate
    private Date createdDate = new Date();

    @Column(name = "updated_date")
//    @LastModifiedDate
    private Date updatedDate = new Date();

    @OneToOne
//    @JsonIgnoreProperties
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public Shipment(Order order, String address, String codeShip){
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.order = order;
        this.address = address;
        this.codeShip = codeShip;
    }
}
