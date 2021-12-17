package com.example.jpapractice.controller;

import com.example.jpapractice.domain.AlbumInfo;
import com.example.jpapractice.domain.SongStatus;
import com.example.jpapractice.domain.dto.*;
import com.example.jpapractice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    // 1. Album, Song 한번에 생성하기
    @PostMapping("/albums-create")
    public CoreRes create(@RequestBody AlbumReq req) {
        return albumService.create(req);
    }

    // 2. Album, Song 한번에 생성하기 + Album 내용 수정 및 Song 추가
    @PostMapping("/albums-create-add")
    public CoreRes createAndAdd(@RequestBody AlbumReq req) {
        return albumService.createAndAdd(req);
    }

    // 3. album_id로 album조회(album 상세정보 + Song 목록까지)
    @GetMapping("/albums")
    public List<AlbumSongRes> findByAlbumId(@RequestParam Long album_id) {
        return albumService.findByAlbumId(album_id);
    }

    // 4. 기존 album에 Song 추가하기
    @PostMapping("/albums/song")
    public CoreRes createSong(@RequestBody SongReq songReq, @RequestParam Long id) {
        return albumService.createSong(id, songReq);
    }

    // 5. Song 상태변경
    @PostMapping("/albums/song-status")
    public CoreRes changeSongStatus(@RequestBody SongStatus status, @RequestParam Long id) {
        return albumService.changeSongStatus(status, id);
    }

    // 6. album을 AlbumInfo로 조회
    @GetMapping("/albums/{albumInfo}")
    public List<AlbumSongRes> findByAlbumInfo(@PathVariable AlbumInfo albumInfo) {
        return albumService.findByAlbumInfo(albumInfo);
    }

    // 7. 프로듀서 등록
    @PostMapping("/albums/producers")
    public CoreRes createProducer(@RequestBody ProducerReq producerReq) {
        return albumService.createProducer(producerReq);
    }

    // 8. 프로듀서 정보 수정
    @PatchMapping("/albums/producers/{idx}")
    public CoreRes updateProducer(@RequestBody ProducerReq producerReq, @PathVariable Long idx) {
        return albumService.updateProducer(idx, producerReq);
    }

    // 9. 기존의 노래에 기존의 프로듀서 추가하기
    @PostMapping("/albums/song/add-producer")
    public CoreRes addProducerToSong(@RequestBody IdxListDto idxListDto, @RequestParam Long id) {
        return albumService.addProducerToSong(id, idxListDto.getIdList());
    }

    // 10. 노래와 프로듀서 생성
    @PostMapping("/albums/songs/producers")
    public CoreRes createSongAndProducer(@RequestBody SongProducerReq songProducerReq, @RequestParam Long id) {
        return albumService.createSongProducer(id, songProducerReq);
    }
}
