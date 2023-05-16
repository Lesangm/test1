package net.smlee.studywebfile.services;

import net.smlee.studywebfile.entities.FileEntity;
import net.smlee.studywebfile.mappers.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


@Service
public class FileService {
    private final FileMapper fileMapper;

    @Autowired
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean putFile(HttpServletRequest request, MultipartFile... multipartFiles) throws IOException {
        int inserted = 0;
        for (MultipartFile multipartFile : multipartFiles) {
            FileEntity file = new FileEntity()
                    .setCreatedAt(new Date())
                    .setClientIp(request.getRemoteAddr())
                    .setClientUa(request.getHeader("User-Agent"))
                    .setName(multipartFile.getOriginalFilename())
                    .setSize((int)multipartFile.getSize())
                    .setContentType(multipartFile.getContentType())
                    .setData(multipartFile.getBytes());
            inserted += this.fileMapper.insertFile(file);
        }
        return inserted == multipartFiles.length;
    }

}
