package com.main;

import com.service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Application {
    FileService fileService = new FileService();

    public static void main(String[] args) throws IOException {
        final String filePath = "E:\\files\\text2.txt";
        final String inputFolder = "E:\\files";
        final String outputDirectory = "E:\\output";
        final String regex = "[^-_A-Za-z0-9 ]+";
        Application application = new Application();


       // application.firstPart(inputFolder, outputDirectory,"");

       // application.firstPartSingleFile(filePath,outputDirectory,regex);

        application.secondPart(filePath,outputDirectory,regex);

    }


    public void firstPart(final String inputFolder, final String outputFolder, final String regex) {
        List<String> inputFiles=null;
        if (inputFolder != null) {
                inputFiles = fileService.readFolder(inputFolder);


            for (String fileName : inputFiles)
            {
                firstPartSingleFile(fileName,outputFolder,regex);
            }
        }
    }


    public boolean firstPartSingleFile(String inputFile, String outputDirectory, String regex) {
        if (inputFile == null || outputDirectory == null) {
            System.out.println(" invalid input output directory");
            return false;
        }

        if (regex == null)
            regex = "";
        try {
            Optional<String> content = fileService.textFileToString(inputFile);
            String text = "";
            if (content.isPresent())
                text = content.get().replaceAll(regex, "");
            return fileService.writeIn(inputFile, outputDirectory, text.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean secondPart(String inputFile, String outputDirectory, String regex) {
        if (inputFile == null || outputDirectory == null) {
            System.out.println(" invalid input output directory");
            return false;
        }
        if (regex == null)
            regex = "";

        Map<String, Integer> map = null;
        String text = "";
        try {
            Optional<String> content = fileService.textFileToString(inputFile);
            if (content.isPresent())
                map = fileService.count_freq(content.get(), regex);

            for (Map.Entry<String, Integer> entry :
                    map.entrySet()) {
                text = text + "\n" + entry.getKey() + " -> " + entry.getValue();
            }
            if(text!="" || !text.isEmpty())
            text = text.substring(1, text.length());
            return fileService.writeIn(inputFile, outputDirectory, text);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
