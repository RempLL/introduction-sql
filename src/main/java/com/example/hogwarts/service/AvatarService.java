package com.example.hogwarts.service;

import com.example.hogwarts.entity.Avatar;
import com.example.hogwarts.entity.Student;
import com.example.hogwarts.repository.AvatarRepository;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;

    private final Path pathToAvatarDir;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, @Value("${path.to.avatar.dir}") String pathToAvatarDir, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.pathToAvatarDir = Path.of(pathToAvatarDir);
        this.studentRepository = studentRepository;
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

    public Student uploadAvatar(long id, MultipartFile multipartFile) {
        Student student = studentRepository.findById(id)
                .orElseThrow();
        create(student,multipartFile);
        student.setAvatarUrl("http://localhost:8080/avatars/" + student.getId()+"/from-db");
        return student;
    }

    public List<Avatar> getAll() {
        return avatarRepository.getAll();
    }

    public List<Avatar> getPag(Integer pageNum,Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1,pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
