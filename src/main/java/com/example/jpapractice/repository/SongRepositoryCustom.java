package com.example.jpapractice.repository;

import com.example.jpapractice.domain.dto.AlbumSongRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SongRepositoryCustom {
    List<AlbumSongRes> findByAlbumId(Long album_id);
}
