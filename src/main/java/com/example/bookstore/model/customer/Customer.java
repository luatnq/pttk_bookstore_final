package com.example.bookstore.model.customer;

import com.example.bookstore.model.dto.RegisterDTO;
import com.example.bookstore.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "created_date")
//    @CreatedDate
    private Date createdDate;

    @Column(name = "updated_date")
//    @LastModifiedDate
    private Date updatedDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    private FullName fullName;

    public Customer(RegisterDTO registerDTO, Account account, FullName fullName) throws ParseException {
        this.email = registerDTO.getEmail();
        this.phone = registerDTO.getPhone();
        this.dob = DateUtils.convertStringToDate(registerDTO.getDob(), "yyyy-MM-dd");
        this.createdDate = new Date();
        this.account = account;
        this.fullName = fullName;
    }
}
