package com.evision.task.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileComparisonService {
    public void compareFiles() throws IOException{
        Path fileAPath= Paths.get("./src/main/resources/fileA.txt");
        List<String>fileAWords = extractWordsFromFile(fileAPath);
        System.out.println(fileAWords);

        Path poolPath= Paths.get("./src/main/resources/pool");
        File[] poolFiles = poolPath.toFile().listFiles();
        List<String> poolFileWords = new ArrayList<>();
        for (File file : poolFiles){
            poolFileWords=extractWordsFromFile(file.toPath());
            System.out.println("The score in file "+file.getName()+" is = " +calculateScore(fileAWords,poolFileWords));
        }
    }

    public List<String> extractWordsFromFile (Path path) throws IOException {
        List<String> words = new ArrayList<>();
        String[] wordArray;
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
            String line;
            while ((line = reader.readLine()) != null){
                wordArray = line.toLowerCase().replaceAll("[^a-zA-Z]+", " ").trim().split("\\s+");
                for (String word : wordArray) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
        }
        return words;
    }
    public double calculateScore(List<String> fileAWords , List<String> poolFileWords){
        if (fileAWords.isEmpty())
            return 0.0;
        else {
            List<String> intersection=new ArrayList<>(fileAWords);
            intersection.retainAll(poolFileWords);
//            System.out.println("intersection : "+ intersection);
            return (double)intersection.size()/fileAWords.size() * 100.0;
        }
    }


}
