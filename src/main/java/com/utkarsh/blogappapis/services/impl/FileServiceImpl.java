package com.utkarsh.blogappapis.services.impl;

import com.utkarsh.blogappapis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        System.out.println(name);
        String randomName = UUID.randomUUID().toString();
        String fileName = randomName.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path+File.separator+fileName;

        File folder = new File(path);
        if (!folder.exists()){
            folder.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        return new FileInputStream(fullPath);
    }
}
