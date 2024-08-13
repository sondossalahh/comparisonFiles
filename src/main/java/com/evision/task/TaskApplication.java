package com.evision.task;

import com.evision.task.controller.FileComparisonController;
import com.evision.task.service.FileComparisonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TaskApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(TaskApplication.class, args);
		FileComparisonService fileComparisonService=new FileComparisonService();
		FileComparisonController fileComparisonController = new FileComparisonController(fileComparisonService);
		fileComparisonController.compareFiles();

	}
}
