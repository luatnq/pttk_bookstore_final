package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "path")
    private String path;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "extension")
    private String extension;

    public FileDb(String path, String extension){
        this.createdDate = new Date();
        this.extension = extension;
        this.path = path;
    }

    public FileDb(String path){
        this.createdDate = new Date();
        this.path = path;
    }
}
