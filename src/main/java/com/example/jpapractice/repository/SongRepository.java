package com.example.jpapractice.repository;

import com.example.jpapractice.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long>, SongRepositoryCustom {
}
