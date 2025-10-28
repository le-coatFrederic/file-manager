package com.fredlecoat.file_manager.services;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fredlecoat.file_manager.entities.FileEntity;
import com.fredlecoat.file_manager.repositories.FileEntityRepository;
import com.fredlecoat.file_manager.values.FileType;

@Service
public class FileScannerService {
    private final FileEntityRepository fileRepository;

    @Value("${app.input-path:/data/input}")
    private String inputPath;

    public FileScannerService(
        FileEntityRepository fileRepository
    ) {
        this.fileRepository = fileRepository;
    }

    public void scanInputFolder() throws IllegalAccessException {
        File inputDir = new File(this.inputPath);
        if (!inputDir.exists() || !inputDir.isDirectory()) {
            throw new IllegalAccessException("Input folder does not exists !");
        }

        this.processCurrentFile(inputDir, null);
    }

    private void processCurrentFile(File folder, FileEntity parentFolder) { 
        System.out.println("FileEntity creation.");       
        FileEntity newFile = new FileEntity(
            folder.getName(), 
            folder.isDirectory() == true ? FileType.FOLDER : FileType.FILE, 
            100L,
            LocalDateTime.ofInstant(Instant.ofEpochMilli(folder.lastModified()), ZoneId.systemDefault())
        );
        
        System.out.println("FileEntity created.");       

        System.out.println("Setting parent.");  
        newFile.setParent_folder(parentFolder != null ? parentFolder : null);
        System.out.println("Parent setted.");  

        this.fileRepository.save(newFile);

        /*for (File file: folder.listFiles()) {
            this.processCurrentFile(file, newFile);
        }*/
    }
}
