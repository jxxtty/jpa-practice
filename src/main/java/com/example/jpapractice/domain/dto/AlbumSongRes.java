package com.example.jpapractice.domain.dto;

import com.example.jpapractice.domain.AlbumInfo;
import com.example.jpapractice.domain.SongStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class AlbumSongRes {
    private Long album_id;
    private String albumName;
    private String artist;
    private String releaseDate;
    private String albumInfo;

    private Long song_id;
    private String title;
    private SongStatus songStatus;

    @QueryProjection
    public AlbumSongRes(Long album_id, String albumName, String artist, LocalDateTime releaseDate, AlbumInfo albumInfo,
                        Long song_id, String title, SongStatus songStatus) {
        this.album_id = album_id;
        this.albumName = albumName;
        this.artist = artist;
        this.releaseDate = releaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.albumInfo = albumInfo.getInfoName();
        this.song_id = song_id;
        this.title = title;
        this.songStatus = songStatus;
    }
}
