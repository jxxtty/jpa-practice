package com.example.jpapractice.controller;

import com.example.jpapractice.domain.dto.AlbumReq;
import com.example.jpapractice.domain.dto.AlbumSongRes;
import com.example.jpapractice.domain.dto.CoreRes;
import com.example.jpapractice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping("/albums-create")
    public CoreRes create(@RequestBody AlbumReq req) {
        return albumService.create(req);
    }

    @PostMapping("/albums-create-add")
    public CoreRes createAndAdd(@RequestBody AlbumReq req) {
        return albumService.createAndAdd(req);
    }


    @GetMapping("/albums")
    public List<AlbumSongRes> findByAlbumId(@RequestParam Long album_id) {
        return albumService.findByAlbumId(album_id);
    }
}
