package com.example.jpapractice.controller;

import com.example.jpapractice.domain.dto.AlbumReq;
import com.example.jpapractice.domain.dto.CoreRes;
import com.example.jpapractice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping("/albums")
    public CoreRes createAndAdd(@RequestBody AlbumReq req) {
        return albumService.createAndAdd(req);
    }
}
