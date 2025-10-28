package com.fredlecoat.file_manager.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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

    @Value("${app.paths.input:/data/input}")
    private String inputPath;

    @Value("${app.paths.temp}")
    private String tmpDir;

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

        for (File file: inputDir.listFiles()) {
            this.processCurrentFile(file, null);
        }
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
        if (parentFolder != null) {
            newFile.setParent_folder(parentFolder);
        }
        System.out.println("Parent setted.");  

        this.fileRepository.save(newFile);

        if (folder.isDirectory()) {
            for (File file: folder.listFiles()) {
                this.processCurrentFile(file, newFile);
            }
        } else {
            moveToTmp(folder.toPath());
        }
    }

    private void moveToTmp(Path file) {
        try {
            Path tmpPath = Path.of(this.tmpDir);
            Path target = tmpPath.resolve(file.getFileName());
            Files.move(file, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
