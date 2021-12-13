package com.example.jpapractice.repository;

import com.example.jpapractice.domain.AlbumInfo;
import com.example.jpapractice.domain.QAlbum;
import com.example.jpapractice.domain.QSong;
import com.example.jpapractice.domain.dto.AlbumSongRes;
import com.example.jpapractice.domain.dto.QAlbumSongRes;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.example.jpapractice.domain.QAlbum.*;
import static com.example.jpapractice.domain.QSong.*;

public class SongRepositoryImpl implements SongRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public SongRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<AlbumSongRes> findByAlbumId(Long album_id) {
        return queryFactory.select(
                        new QAlbumSongRes(
                                album.id,
                                album.albumName,
                                album.artist,
                                album.releaseDate,
                                album.albumInfo,
                                song.id,
                                song.title,
                                song.songStatus
                        )).from(song)
                .leftJoin(song.album, album)
                .where(album.id.eq(album_id))
                .fetch();
    }

    @Override
    public List<AlbumSongRes> findByAlbumInfo(AlbumInfo albumInfo) {
        return queryFactory.select(
                        new QAlbumSongRes(
                                album.id,
                                album.albumName,
                                album.artist,
                                album.releaseDate,
                                album.albumInfo,
                                song.id,
                                song.title,
                                song.songStatus
                        )).from(song)
                .leftJoin(song.album, album)
                .where(album.albumInfo.eq(albumInfo))
                .fetch();
    }
}
