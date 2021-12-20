package com.example.jpapractice.service;

import com.example.jpapractice.domain.*;
import com.example.jpapractice.domain.dto.*;
import com.example.jpapractice.repository.AlbumRepository;
import com.example.jpapractice.repository.ProducerRepository;
import com.example.jpapractice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ProducerRepository producerRepository;

    // 1. 앨범 생성 및 노래 추가
    public CoreRes create(AlbumReq req) {
        // Album 생성 및 저장
        Album newAlbum = makeNewAlbum(req.getAlbumName(), req.getArtist(), req.getReleaseDate(), req.getAlbumInfo());
        albumRepository.save(newAlbum);

        //
        int size = req.getTimes().size();
        for (int i = 0; i < size; i++) {
            Song newSong = makeNewSong(newAlbum, req.getTitles().get(i), req.getTimes().get(i), req.getSongStatuses().get(i));
            songRepository.save(newSong);
        }

        return new CoreRes(HttpStatus.CREATED, "앨범 생성/수정 완료");
    }

    public CoreRes createAndAdd(AlbumReq req) {
        // Album 생성 및 저장
        // album_id가 들어온 경우, 기존 album의 내용을 수정하고 추가로 song을 저장한다
        // album_id가 들어오지 않은 경우, album도 새로생성하고 추가로 song도 저장한다
        Album album = null;
        if(req.getAlbum_id() != null){
            album = albumRepository.findById(req.getAlbum_id()).orElseThrow(() -> new IllegalArgumentException("해당 앨범이 존재하지 않습니다."));
            album.changeAlbumDetail(req.getAlbumName(), req.getArtist(), req.getReleaseDate(), req.getAlbumInfo());
        } else {
            album = makeNewAlbum(req.getAlbumName(), req.getArtist(), req.getReleaseDate(), req.getAlbumInfo());
        }
        albumRepository.save(album);

        if(req.getTitles() == null) return new CoreRes(HttpStatus.OK, "앨범 수정 완료");

        int size = req.getTimes().size();
        for (int i = 0; i < size; i++) {
            Song newSong = makeNewSong(album, req.getTitles().get(i), req.getTimes().get(i), req.getSongStatuses().get(i));
            songRepository.save(newSong);
        }

        return new CoreRes(HttpStatus.CREATED, "앨범 수정 및 노래 추가 완료");
    }

    // 2. 앨범아이디로 앨범+노래 조회하기
    public List<AlbumSongRes> findByAlbumId(Long album_id){
        return songRepository.findByAlbumId(album_id);
    }


    public Album makeNewAlbum(String albumName, String artist, LocalDateTime releaseDate, AlbumInfo albumInfo) {
        return Album.builder()
                .albumName(albumName)
                .artist(artist)
                .releaseDate(releaseDate)
                .albumInfo(albumInfo)
                .build();
    }




    // ------------------------------- Song ----------------------------------

    public CoreRes createSong(Long album_id, SongReq songReq) {
        Album findAlbum = albumRepository.findById(album_id).orElseThrow(() -> new IllegalArgumentException("해당 앨범을 찾을 수 없습니다."));

        // 1. @DynamicInsert 안넣고 되는지? 된다면 nullable=true 로 지정해도 그런지? 확인하기
        Song newSong = makeNewSong(findAlbum, songReq.getTitle(), songReq.getTime(), songReq.getSongStatus());

        songRepository.save(newSong);
        return new CoreRes(HttpStatus.CREATED, "노래 추가 생성 완료");
    }

    @Transactional
    public CoreRes changeSongStatus(SongStatus status, Long song_id) {
        Song findSong = songRepository.findById(song_id).orElseThrow(() -> new IllegalArgumentException("해당 노래를 찾을 수 없습니다."));
        findSong.changeSongStatus(status);

        return new CoreRes(HttpStatus.OK, "노래 상태 수정 완료");
    }

    public List<AlbumSongRes> findByAlbumInfo(AlbumInfo albumInfo) {
        return songRepository.findByAlbumInfo(albumInfo);
    }


    public Song makeNewSong(Album album, String title, Integer time, SongStatus songStatus) {
        return Song.builder()
                .album(album)
                .title(title)
                .time(time)
                .songStatus(songStatus)
                .build();
    }


    // ------------------------------- Producer ----------------------------------

    // 프로듀서 생성
    // 1. 프로듀서만 등록 -> 기존에 추가되어있는 프로듀서를 Song에 추가
    // 2. 프로듀서 등록과 동시에 Song에 해당 프로듀서 추가

    // 프로듀서 생성(Song이 있다면 Song에 추가까지)
    public CoreRes createProducer(ProducerReq producerReq) {
        Song song = null;
        if(producerReq.getSong_id() != null)
            song = songRepository.findById(producerReq.getSong_id()).orElseThrow(() -> new IllegalArgumentException("해당하는 노래를 찾을 수 없습니다."));

        Producer newProducer = makeNewProducer(producerReq.getProducerType(),
                                                producerReq.getProducer_name(),
                                                producerReq.getProducer_nickname(),
                                                song);

        producerRepository.save(newProducer);

        return new CoreRes(HttpStatus.CREATED, "프로듀서 생성 완료");
    }


    // 프로듀서 수정
    @Transactional
    public CoreRes updateProducer(Long id, ProducerReq producerReq) {
        Producer findProducer = producerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 프로듀서를 찾을 수 없습니다."));

        findProducer.updateProducer(producerReq);

        return new CoreRes(HttpStatus.OK, "프로듀서 수정 완료");
    }


    // 기존에 추가되어있는 프로듀서 Song과 연결하기
    @Transactional
    public CoreRes addProducerToSong(Long song_id, List<Long> producer_ids) {
        Song findSong = songRepository.findById(song_id).orElseThrow(() -> new IllegalArgumentException("해당하는 노래를 찾을 수 없습니다."));

        for (Long idx : producer_ids) {
            Producer findProducer = producerRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당하는 프로듀서를 찾을 수 없습니다."));

            findProducer.changeSong(findSong);
        }

        return new CoreRes(HttpStatus.OK, "기존 프로듀서를 노래에 추가 완료");
    }


    // Album, Song, Producer 한번에 생성하기
    public CoreRes createSongProducer(Long album_id, SongProducerReq songProducerReq) {
        Album findAlbum = albumRepository.findById(album_id).orElseThrow(() -> new IllegalArgumentException("해당하는 앨범을 찾을 수 없습니다."));

        Song newSong = makeNewSong(findAlbum, songProducerReq.getTitle(), songProducerReq.getTime(), songProducerReq.getSongStatus());
        songRepository.save(newSong);

        int size = songProducerReq.getProducerNames().size();
        for (int i = 0; i < size; i++) {
            Producer newProducer = makeNewProducer(songProducerReq.getProducerTypes().get(i),
                                                    songProducerReq.getProducerNames().get(i),
                                                    songProducerReq.getProducerNicknames().get(i),
                                                    newSong);
            producerRepository.save(newProducer);
        }

        return new CoreRes(HttpStatus.CREATED, "노래, 프로듀서 생성 완료");
    }

    public Producer makeNewProducer(ProducerType producerType, String producer_name, String producer_nickname, Song song) {
        return Producer.builder()
                .producerType(producerType)
                .producer_name(producer_name)
                .producer_nickname(producer_nickname)
                .song(song)
                .build();
    }
}
