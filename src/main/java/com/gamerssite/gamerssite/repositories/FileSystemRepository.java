package com.gamerssite.gamerssite.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Repository
public class FileSystemRepository {

    @Value("${upload.path}")
    private String uploadPath;

    public String save(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String resultFilename = uuid + "-" + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + resultFilename));

        return resultFilename;
    }

    public void delete(String fileName) {
        File file = new File(uploadPath + "/" + fileName);
        file.delete();
    }
}
