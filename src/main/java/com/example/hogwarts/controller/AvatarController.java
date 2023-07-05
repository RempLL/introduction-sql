package com.example.hogwarts.controller;

import com.example.hogwarts.entity.Avatar;
import com.example.hogwarts.entity.Student;
import com.example.hogwarts.service.AvatarService;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/{id}/from-db")
    public ResponseEntity<byte[]> getFromDb(@PathVariable long id) {
        return build(avatarService.getFromDb(id));
    }

    @GetMapping("/{id}/from-fs")
    public ResponseEntity<byte[]> getFromFs(@PathVariable long id) {
        return build(avatarService.getFromFs(id));
    }

    @PatchMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Student uploadAvatar(@PathVariable long id, @RequestParam("avatar") MultipartFile multipartFile){
        return avatarService.uploadAvatar(id,multipartFile);
    }

    @GetMapping("/getAll")
    public List<Avatar> getAll() {
        return avatarService.getAll();
    }

    @GetMapping("/getPag")
    public ResponseEntity<List<Avatar>> getPag(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = avatarService.getPag(pageNum,pageSize);
        return ResponseEntity.ok(avatars);
    }

    private ResponseEntity<byte[]> build(Pair<byte[],String> pair){
        byte[] data = pair.getFirst();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(pair.getSecond())).contentLength(data.length).body(data);
    }
}
