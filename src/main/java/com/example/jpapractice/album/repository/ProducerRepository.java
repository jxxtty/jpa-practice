package com.example.jpapractice.album.repository;

import com.example.jpapractice.album.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
