package com.example.bookstore.model.book;

import com.example.bookstore.model.FileDb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "book_items")
@AllArgsConstructor
@NoArgsConstructor
public class BookItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bar_code")
    private String barCode;

    @Column(name = "price")
    private float price;

    @Column(name = "discount")
    private String discount;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_item_file",
            joinColumns = @JoinColumn(name = "book_item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id"))
    private Set<FileDb> fileDbs = new HashSet<>();

    public BookItem (String  barCode, String discount, float price, Book book, Set<FileDb> fileDbs){
        this.barCode = barCode;
        this.discount = discount;
        this.price = price;
        this.book = book;
        this.fileDbs = fileDbs;
    }

    public BookItem(Book book, float price, String discount, String barCode){
        this.barCode = barCode;
        this.discount = discount;
        this.price = price;
        this.book = book;
        this.fileDbs.addAll(fileDbs);
        this.createdDate = new Date();
    }
}
