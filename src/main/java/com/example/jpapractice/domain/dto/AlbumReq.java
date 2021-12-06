package com.example.jpapractice.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumReq {
    // Album
    private Long album_id;
    private String albumName;
    private String artist;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseDate;

    // Song
    private String title;
    private Integer time;
    private String composer;
    private String lyricist;
}
