package com.example.jpapractice.domain.dto;

import com.example.jpapractice.domain.AlbumInfo;
import com.example.jpapractice.domain.ProducerType;
import com.example.jpapractice.domain.SongStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private AlbumInfo albumInfo;

    // Song
    private List<String> titles;
    private List<Integer> times;
    private List<SongStatus> songStatuses;

}
