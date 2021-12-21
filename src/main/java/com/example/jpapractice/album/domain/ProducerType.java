package com.example.jpapractice.album.domain;

public enum ProducerType {
    COMPOSER("작곡가"),
    LYRICIST("작사가");

    private String producerName;

    private ProducerType(String producerName){ this.producerName = producerName; }

    public String getProducerName() {
        return producerName;
    }
}
