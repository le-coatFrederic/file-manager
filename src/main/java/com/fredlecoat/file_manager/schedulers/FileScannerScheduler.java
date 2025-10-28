package com.fredlecoat.file_manager.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fredlecoat.file_manager.services.FileScannerService;

@Component
public class FileScannerScheduler {
    private final FileScannerService fileScannerService;

    public FileScannerScheduler(
        FileScannerService fileScannerService
    ) {
        this.fileScannerService = fileScannerService;
    }

    @Scheduled(fixedDelay = 1000000)
    public void scanInputFolder() {
        try {
            this.fileScannerService.scanInputFolder();
        } catch (IllegalAccessException e) {
            System.err.println("Can't scan input folder : " + e.getLocalizedMessage());
        }
    }
}
