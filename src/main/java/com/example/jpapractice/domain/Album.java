package com.example.jpapractice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Song> songs = new ArrayList<>();

}
