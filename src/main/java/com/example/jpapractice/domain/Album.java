package com.example.jpapractice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Album extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    private String albumName; // 앨범이름

    private String artist; // 가수

    private LocalDateTime releaseDate; // 발매일

    @Builder.Default // 빌더는 초기화를 무시한다. 이 어노테이션을 달아주면 초기화를 유지한다.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Song> songs = new ArrayList<>();


    public void changeAlbumDetail(String albumName, String artist, LocalDateTime releaseDate) {
        this.albumName = albumName;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }
}
