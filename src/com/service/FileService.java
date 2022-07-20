package com.service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileService {

    public boolean writeInTextFile(final String fileName, final String text) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(text);
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public List<String> readFolder(final String inputFolder) {
        List<String> list=new ArrayList<>();
        if(inputFolder!=null) {
            final File folder = new File(inputFolder);

            if(folder.canRead())
                 list.addAll(getAllFileFromFolder(folder));
            else {
                System.out.println("folder is not readable ");
                return list;
            }
        }
        return list;
    }



    public boolean writeIn(String inputFile, String outputDirectory, String text) {
        if(inputFile==null || outputDirectory==null || text==null){
            System.out.println("invalid input output directory"  );
            return false;
        }

        Optional<String> fileTypeOp = getExtension(inputFile);

        if (fileTypeOp.isPresent())
            switch (fileTypeOp.get()) {
                case "txt":
                    return writeInTextFileDirectory(outputDirectory, inputFile, text);
                default:
                    System.out.println("file Type = " + fileTypeOp.get()+" is not supported");
                    return false;
            }

        return false;
    }

    public List<String> getAllFileFromFolder(final File folder){
        List<String> fileList=new ArrayList<>();
        try {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    getAllFileFromFolder(fileEntry);
                } else {
                    fileList.add(fileEntry.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));
                }
            }
            return fileList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean writeInTextFileDirectory(final String directory,String filePath,final String text){
        String fileName=getFileName(filePath);
        if(text!=null)
           return writeInTextFile(directory+"\\"+fileName,text);

        return false;
    }


    public String getFileName(String filePath){
        try {
            Path path = Paths.get(filePath);
            return  path.getFileName().toString();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    public Optional<String> getExtension(final String filename) {
        return Optional.ofNullable(filename).filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public Optional<String> textFileToString(final String fileName) throws IOException {
        if(fileName!=null) {
            String result = "";
            BufferedReader br = getBufferedReader(fileName);

            if (br != null) {
                String str = null;
                while ((str = br.readLine()) != null) {
                    result = result + " \n" + str;
                }
                if (result != "")
                    result = result.substring(2, result.length());
            }
            return Optional.ofNullable(result.trim());
        }
        return Optional.ofNullable(null);
    }

    public BufferedReader getBufferedReader(final String filePath){
        File file = new File(filePath);
        FileReader fr=null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return new BufferedReader(fr);
    }

    public Map<String, Integer> count_freq(String str, String regex) {
        Map<String, Integer> mp = new HashMap<>();
        if(str!=null && regex!=null) {
            String arr[] = str.split(" ");

            for (int i = 0; i < arr.length; i++) {
                String key = arr[i].toLowerCase();
                key = key.replaceAll(regex, "");
                if (mp.containsKey(key)) {
                    mp.put(key, mp.get(key) + 1);
                } else {
                    mp.put(key, 1);
                }
            }
            if (mp.containsKey(""))
                mp.remove("");
        }
        return mp;
    }
}
