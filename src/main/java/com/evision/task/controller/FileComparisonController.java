package com.evision.task.controller;

import com.evision.task.service.FileComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileComparisonController {
    FileComparisonService fileComparisonService;

    @Autowired
    public FileComparisonController(FileComparisonService fileComparisonService) {
        this.fileComparisonService = fileComparisonService;
    }
    @GetMapping("/compare")
    public String compareFiles() {
        try {
            fileComparisonService.compareFiles();
            return "Comparison completed successfully";
        } catch (IOException e) {
            return "An error occurred during file comparison: " + e.getMessage();
        }
    }
}
