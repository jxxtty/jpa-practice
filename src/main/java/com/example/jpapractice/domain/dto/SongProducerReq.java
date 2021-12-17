package com.example.jpapractice.domain.dto;

import com.example.jpapractice.domain.AlbumInfo;
import com.example.jpapractice.domain.ProducerType;
import com.example.jpapractice.domain.SongStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SongProducerReq {
    // Song
    private String title;
    private Integer time;
    private SongStatus songStatus;

    // Producer
    private List<ProducerType> producerTypes;
    private List<String> producerNames;
    private List<String> producerNicknames;


}
