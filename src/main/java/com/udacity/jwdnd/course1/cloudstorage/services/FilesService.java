package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService {
    private final FileMapper fileMapper;
    private final Logger logger = LoggerFactory.getLogger(FilesService.class);

    public FilesService(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }
   public int storeFile(MultipartFile file, Integer userId) {
        try {
            File uploadFile = new File(file.getOriginalFilename(),file.getContentType(),String.valueOf(file.getSize()), userId,file.getBytes());
            return fileMapper.saveFile(uploadFile);
        }catch (IOException e){
            logger.error(e.getMessage());
        }
        return -1;
   }


    public File loadFile(String fileName,Integer userId) {
        return fileMapper.getFileForUserByName(userId,fileName);
    }
    public boolean isFileAlreadyExists(String fileName,Integer userId) {
        return fileMapper.getFileForUserByName(userId,fileName) != null;
    }

    public int deleteFile(String fileName,Integer userId) {
        return fileMapper.deleteFile(userId,fileName);
    }
}
