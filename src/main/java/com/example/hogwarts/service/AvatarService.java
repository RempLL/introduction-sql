package com.example.hogwarts.service;

import com.example.hogwarts.entity.Avatar;
import com.example.hogwarts.entity.Student;
import com.example.hogwarts.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;

    private final Path pathToAvatarDir;

    public AvatarService(AvatarRepository avatarRepository, @Value("${path.to.avatar.dir}") String pathToAvatarDir) {
        this.avatarRepository = avatarRepository;
        this.pathToAvatarDir = Path.of(pathToAvatarDir);
    }

    public Avatar create(Student student, MultipartFile multipartFile) {
        try {
            String contentType = multipartFile.getContentType();
            String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
            byte[] data = multipartFile.getBytes();
            String fileName = UUID.randomUUID() + "." +extension;
            Path pathToAvatar = pathToAvatarDir.resolve(fileName);
            Files.write(pathToAvatar,data);

            Avatar avatar = avatarRepository.findByStudent_id(student.getId()).orElse(new Avatar());

            if(avatar.getFilePath()!=null){
                Files.delete(Path.of(avatar.getFilePath()));
            }
            avatar.setMediaType(contentType);
            avatar.setFileSize(data.length);
            avatar.setData(data);
            avatar.setStudent(student);
            avatar.setFilePath(pathToAvatar.toString());
            return avatarRepository.save(avatar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<byte[],String> getFromDb(long id){
        Avatar avatar = avatarRepository.findByStudent_id(id).orElseThrow();
        return Pair.of(avatar.getData(),avatar.getMediaType());
    }

    public Pair<byte[],String> getFromFs(long id){
        try {
            Avatar avatar = avatarRepository.findByStudent_id(id).orElseThrow();
            return Pair.of(Files.readAllBytes(Path.of(avatar.getFilePath())),avatar.getMediaType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
