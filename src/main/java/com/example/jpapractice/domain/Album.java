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

    // 국내에 앨범1개, 해외에 앨범1개 라고 가정한다.
    @Enumerated(EnumType.STRING)
    private AlbumInfo albumInfo; // 앨범정보(국내, 해외)

    @Builder.Default // 빌더는 초기화를 무시한다. 이 어노테이션을 달아주면 초기화를 유지한다.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Song> songs = new ArrayList<>();


    public void changeAlbumDetail(String albumName, String artist, LocalDateTime releaseDate, AlbumInfo albumInfo) {
        this.albumName = albumName;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.albumInfo = albumInfo;
    }
}
