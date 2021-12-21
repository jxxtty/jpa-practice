package com.example.jpapractice.album.repository;

import com.example.jpapractice.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
