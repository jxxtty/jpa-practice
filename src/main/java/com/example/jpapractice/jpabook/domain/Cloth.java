package com.example.jpapractice.jpabook.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter @Getter
@DiscriminatorValue("C")
@Entity
public class Cloth extends Item{

    private String brandName;
    private String category;
}
