package com.example.jpapractice.album.repository;

import com.example.jpapractice.album.domain.AlbumInfo;
import com.example.jpapractice.album.domain.dto.AlbumSongRes;

import java.util.List;

public interface SongRepositoryCustom {
    List<AlbumSongRes> findByAlbumId(Long album_id);

    List<AlbumSongRes> findByAlbumInfo(AlbumInfo albumInfo);
}
