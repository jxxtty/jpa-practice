package com.example.jpapractice.service;

import com.example.jpapractice.domain.dto.AlbumReq;
import com.example.jpapractice.domain.dto.CoreRes;
import com.example.jpapractice.repository.AlbumRepository;
import com.example.jpapractice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public CoreRes createAndAdd(AlbumReq req) {

        return new CoreRes(HttpStatus.CREATED, "앨범 생성/수정 완료");
    }
}
