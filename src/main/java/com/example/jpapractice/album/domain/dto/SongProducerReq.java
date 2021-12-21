package com.example.jpapractice.album.domain.dto;

import com.example.jpapractice.album.domain.ProducerType;
import com.example.jpapractice.album.domain.SongStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

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
