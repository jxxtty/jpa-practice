package com.example.jpapractice.domain.dto;

import com.example.jpapractice.domain.ProducerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerReq {
    private ProducerType producerType;
    private String producer_name;
    private String producer_nickname;
    private Long song_id;
}
