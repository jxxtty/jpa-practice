package com.example.jpapractice.service;

import com.example.jpapractice.domain.Album;
import com.example.jpapractice.domain.Song;
import com.example.jpapractice.domain.SongStatus;
import com.example.jpapractice.domain.dto.AlbumReq;
import com.example.jpapractice.domain.dto.AlbumSongRes;
import com.example.jpapractice.domain.dto.CoreRes;
import com.example.jpapractice.domain.dto.SongReq;
import com.example.jpapractice.repository.AlbumRepository;
import com.example.jpapractice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public CoreRes create(AlbumReq req) {
        // Album 생성 및 저장
        Album newAlbum =
                Album.builder()
                .albumName(req.getAlbumName())
                .artist(req.getArtist())
                .releaseDate(req.getReleaseDate())
                .build();
        albumRepository.save(newAlbum);

        //
        int size = req.getTimes().size();
        for (int i = 0; i < size; i++) {
            Song newSong = Song.builder()
                    .album(newAlbum)
                    .title(req.getTitles().get(i))
                    .time(req.getTimes().get(i))
                    .composer(req.getComposers().get(i))
                    .lyricist(req.getLyricists().get(i))
                    .songStatus(req.getSongStatuses().get(i))
                    .build();
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
            album.changeAlbumDetail(req.getAlbumName(), req.getArtist(), req.getReleaseDate());
        } else {
            album =
                    Album.builder()
                            .albumName(req.getAlbumName())
                            .artist(req.getArtist())
                            .releaseDate(req.getReleaseDate())
                            .build();
        }
        albumRepository.save(album);

        int size = req.getTimes().size();
        for (int i = 0; i < size; i++) {
            Song newSong = Song.builder()
                    .album(album)
                    .title(req.getTitles().get(i))
                    .time(req.getTimes().get(i))
                    .composer(req.getComposers().get(i))
                    .lyricist(req.getLyricists().get(i))
                    .songStatus(req.getSongStatuses().get(i))
                    .build();
            songRepository.save(newSong);
        }

        return new CoreRes(HttpStatus.CREATED, "앨범 생성/수정 완료");
    }

    public List<AlbumSongRes> findByAlbumId(Long album_id){
        return songRepository.findByAlbumId(album_id);
    }

    public CoreRes createSong(Long album_id, SongReq songReq) {
        Album findAlbum = albumRepository.findById(album_id).orElseThrow(() -> new IllegalArgumentException("해당 앨범을 찾을 수 없습니다."));

        // 1. @DynamicInsert 안넣고 되는지? 된다면 nullable=true 로 지정해도 그런지? 확인하기
        Song newSong = Song.builder()
                .album(findAlbum)
                .title(songReq.getTitle())
                .time(songReq.getTime())
                .composer(songReq.getComposer())
                .lyricist(songReq.getLyricist())
                .songStatus(songReq.getSongStatus())
                .build();

        songRepository.save(newSong);
        return new CoreRes(HttpStatus.CREATED, "노래 추가 생성 완료");
    }

    @Transactional
    public CoreRes changeSongStatus(SongStatus status, Long song_id) {
        Song findSong = songRepository.findById(song_id).orElseThrow(() -> new IllegalArgumentException("해당 노래를 찾을 수 없습니다."));
        findSong.changeSongStatus(status);

        return new CoreRes(HttpStatus.OK, "노래 상태 수정 완료");
    }
}
