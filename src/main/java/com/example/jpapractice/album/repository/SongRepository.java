package com.example.jpapractice.album.repository;

import com.example.jpapractice.album.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long>, SongRepositoryCustom {
}
