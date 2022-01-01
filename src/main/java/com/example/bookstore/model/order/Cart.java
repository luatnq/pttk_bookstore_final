package com.example.bookstore.model.order;

import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Table(name = "carts")
@AllArgsConstructor
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status")
    private int status;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    private Date updatedDate = new Date();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = BookItem.class)
    @JoinTable(name = "cart_book_item",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_item_id", referencedColumnName = "id"))
    private Set<BookItem> bookItems = new LinkedHashSet<>();

    public Cart(){
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public Cart(float totalPrice, int amount, int status, Customer customer, BookItem bookItem) {
        this.bookItems = new HashSet<>();
        if (Objects.nonNull(bookItem)) {
            this.bookItems.add(bookItem);
        }
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.status = status;
        this.customer = customer;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
}
