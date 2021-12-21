package com.example.jpapractice.album.repository;

import com.example.jpapractice.album.domain.AlbumInfo;
import com.example.jpapractice.album.domain.dto.AlbumSongRes;
import com.example.jpapractice.album.domain.dto.QAlbumSongRes;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.example.jpapractice.album.domain.QAlbum.*;
import static com.example.jpapractice.album.domain.QSong.*;

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
