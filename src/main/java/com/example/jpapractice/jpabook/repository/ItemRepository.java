package com.example.jpapractice.jpabook.repository;

import com.example.jpapractice.jpabook.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
