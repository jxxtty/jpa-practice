package com.example.jpapractice.service;

import com.example.jpapractice.domain.Album;
import com.example.jpapractice.domain.Song;
import com.example.jpapractice.domain.dto.AlbumReq;
import com.example.jpapractice.domain.dto.CoreRes;
import com.example.jpapractice.repository.AlbumRepository;
import com.example.jpapractice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        log.info(">>>>>>>>>> newAlbum.getId() " + newAlbum.getId());

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
            log.info("----------------------> Song 저장완료");
        }

        return new CoreRes(HttpStatus.CREATED, "앨범 생성/수정 완료");
    }
}
