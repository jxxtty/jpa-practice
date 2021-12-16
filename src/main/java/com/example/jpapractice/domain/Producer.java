package com.example.jpapractice.domain;

import com.example.jpapractice.domain.dto.ProducerReq;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Producer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProducerType producerType;

    private String producer_name;

    private String producer_nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    public Producer(ProducerType producerType, String producer_name, String producer_nickname, Song song) {
        this.producerType = producerType;
        this.producer_name = producer_name;
        this.producer_nickname = producer_nickname;
        if(song != null) changeSong(song);
    }

    // 수정 메소드
    public void updateProducer(ProducerReq producerReq) {
        this.producerType = producerReq.getProducerType();
        this.producer_name = producerReq.getProducer_name();
        this.producer_nickname = producerReq.getProducer_nickname();
    }

    // 연관관계 편의 메소드
    public void changeSong(Song song) {
        if(this.song != null) this.song.getProducers().remove(this);
        this.song = song;
        song.getProducers().add(this);
    }
}
