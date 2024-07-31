package com.library.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String userName;
    private String password;
    private String role;

}
