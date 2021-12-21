package com.example.jpapractice.jpabook.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter @Setter
@PrimaryKeyJoinColumn(name = "book_id") // 이렇게 따로 설정해주지 않으면 부모테이블의 id(item_id)명을 그대로 사용한다.
@DiscriminatorValue("B")
@Entity
public class Book extends Item{
    private String author;
    private String isbn;
}
