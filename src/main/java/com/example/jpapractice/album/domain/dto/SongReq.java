package com.example.jpapractice.album.domain.dto;

import com.example.jpapractice.album.domain.SongStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongReq {
    private String title;
    private Integer time;
    private String composer;
    private String lyricist;
    private SongStatus songStatus;
}
