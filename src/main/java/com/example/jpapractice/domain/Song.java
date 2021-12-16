package com.example.jpapractice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Song extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    private String title; // 제목

    private Integer time; // 재생시간

    @Builder.Default
    @OneToMany(mappedBy = "song")
    private List<Producer> producers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SongStatus songStatus;

    // 곡 상태변경 메소드
    public void changeSongStatus(SongStatus status) {
        this.songStatus = status;
    }

    public Song(Album album, String title, Integer time, SongStatus songStatus) {
        if(album != null) changeAlbum(album);
        this.title = title;
        this.time = time;
//        this.composer = composer;
//        this.lyricist = lyricist;
        this.songStatus = songStatus;
    }

    // 연관관계 편의 메소드
    public void changeAlbum(Album album) {
        if(this.album != null) this.album.getSongs().remove(this);
        this.album = album;
        album.getSongs().add(this);
    }
}
