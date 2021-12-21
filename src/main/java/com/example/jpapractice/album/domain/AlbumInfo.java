package com.example.jpapractice.album.domain;

public enum AlbumInfo {
    DOMESTIC("국내"),
    OVERSEAS("해외");

    private String infoName;

    private AlbumInfo(String infoName) {
        this.infoName = infoName;
    }
    public String getInfoName() {
        return infoName;
    }
}
