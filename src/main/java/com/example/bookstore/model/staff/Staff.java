package com.example.bookstore.model.staff;

import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.Address;
import com.example.bookstore.model.customer.FullName;
import com.example.bookstore.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;


@Entity
@Table(name = "staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "staff_code")
    private String staffCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "full_name_id")
    private Integer fullNameId;

    @Column(name = "role_id")
    private Integer roleId;

    public Staff(String email, String phone, String dob, Account account, FullName fullName, String staffCode) throws ParseException {
        this.email = email;
        this.phone = phone;
        this.dob = DateUtils.convertStringToDate(dob, "yyyy-MM-dd");
        this.staffCode = staffCode;
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.accountId = account.getId();
        this.fullNameId = fullName.getId();
        this.roleId = 1;
    }
}
