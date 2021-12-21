package com.example.jpapractice.jpabook.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@DiscriminatorColumn // default값이 "DTYPE" -> (name = "DTYPE")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name; // 이름
    private int price; // 가격
}
