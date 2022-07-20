package com.test;

import com.service.FileService;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class FileServiceTest {

    final String inputFile = "E:\\files\\text.txt";
    final String inputFolder = "E:\\files";
    final String outputFolder = "E:\\output";
    final String text = "vkjabklk";
    FileService service = new FileService();

    @Test()
    public void writeInTextFile_valid() {
        boolean r = service.writeInTextFile(inputFile, text);
        assertTrue(r);
    }

    @Test()
    public void writeInTextFile_Invalid() {
        boolean r = service.writeInTextFile("jk" + inputFile, text);
        assertFalse(r);
    }

    @Test()
    public void writeInTextFile_Null() {
        boolean r = service.writeInTextFile(null, text);
        assertFalse(r);
    }

    @Test()
    public void writeInTextFile_Null1() {
        boolean r = service.writeInTextFile(inputFile, null);
        assertFalse(r);
    }

    @Test
    public void readFolder_valid() {
        List<String> list = service.readFolder(inputFolder);
        assertEquals(4, list.size());
    }

    @Test
    public void readFolder_Invalid() {
        List<String> list = service.readFolder("gk" + inputFolder);
        assertEquals(0, list.size());
    }

    @Test
    public void readFolder_Null() {
        List<String> list = service.readFolder(null);
        assertEquals(0, list.size());
    }

    @Test
    public void writeIn_valid() {
        boolean r = service.writeIn(inputFile, outputFolder, text);
        assertTrue(r);
    }

    @Test
    public void writeIn_INvalid() {
        boolean r = service.writeIn("bjh" + inputFile, outputFolder, text);
        assertFalse(r);
    }

    @Test
    public void writeIn_INvalid2() {
        boolean r = service.writeIn(inputFile, "xcv" + outputFolder, text);
        assertFalse(r);
    }

    @Test
    public void writeIn_null() {
        boolean r = service.writeIn(null, "xcv" + outputFolder, text);
        assertFalse(r);
    }

    @Test
    public void writeIn_null2() {
        boolean r = service.writeIn(inputFile, null, text);
        assertFalse(r);
    }

    @Test
    public void getAllFileFromFolder_valid() {
        final File folder = new File(inputFolder);
        List<String> fileList= service.getAllFileFromFolder(folder);
        assertEquals(4,fileList.size());
    }


    @Test
    public void writeInTextFileDirectory_Valid(){
        boolean b=service.writeInTextFileDirectory(outputFolder,inputFile,text);
        assertTrue(b);
    }

    @Test
    public void writeInTextFileDirectory_InValid(){
        boolean b=service.writeInTextFileDirectory("ss"+outputFolder,inputFile,text);
        assertFalse(b);
    }

    @Test
    public void writeInTextFileDirectory_InValid2(){
        boolean b=service.writeInTextFileDirectory(outputFolder,"sd"+inputFile,text);
        assertFalse(b);
    }

    @Test
    public void writeInTextFileDirectory_null(){
        boolean b=service.writeInTextFileDirectory(null,null,null);
        assertFalse(b);
    }

    @Test
    public void getFileName_Valid(){
        String path=service.getFileName(inputFile);
        assertEquals(path,"text.txt");
    }

    @Test
    public void getFileName_InValid(){
        String path=service.getFileName("jkj"+inputFile);
        assertEquals("","");
    }

    @Test
    public void getFileName_Null(){
        String path=service.getFileName(null);
        assertEquals("","");
    }

    @Test
    public void getExtension_valid(){
        Optional<String> ext=service.getExtension(inputFile);
        assertEquals("txt",ext.get());
    }


//    @Test
//    public void textFileToString_valid() throws IOException {
//        Optional<String> strOp=service.textFileToString(inputFile);
//        assertEquals("",strOp.get());
//    }

    @Test
    public void count_freq_valid(){
        String str="hello hello i am good ";
        Map<String, Integer> result =service.count_freq(str,"");
        Map<String,Integer>  map=new HashMap<>();
        map.put("hello",2);
        map.put("i",1);
        map.put("am",1);
        map.put("good",1);

        assertEquals(map,result);
    }


    @Test
    public void count_freq_valid2(){
        String regex = "[ ](?=[ ])|[^-_A-Za-z0-9 ]+";
        String str="hell/o h[ello i ,am good ";
        Map<String, Integer> result =service.count_freq(str,regex);
        Map<String,Integer>  map=new HashMap<>();
        map.put("hello",2);
        map.put("i",1);
        map.put("am",1);
        map.put("good",1);

        assertEquals(map,result);
    }

    @Test
    public void count_freq_valid_Null(){
        String regex = "[ ](?=[ ])|[^-_A-Za-z0-9 ]+";
        String str="hell/o h[ello i ,am good ";
        Map<String, Integer> result =service.count_freq(null,regex);
        Map<String,Integer>  map=new HashMap<>();
//        map.put("hello",2);
//        map.put("i",1);
//        map.put("am",1);
//        map.put("good",1);

        assertEquals(map,result);
    }

    @Test
    public void count_freq_valid_Null2(){
        String regex = "[ ](?=[ ])|[^-_A-Za-z0-9 ]+";
        String str="hell/o h[ello i ,am good ";
        Map<String, Integer> result =service.count_freq(str,null);
        Map<String,Integer>  map=new HashMap<>();
//        map.put("hello",2);
//        map.put("i",1);
//        map.put("am",1);
//        map.put("good",1);

        assertEquals(map,result);
    }

    @Test
    public void count_freq_valid_blank(){
        String regex = "[ ](?=[ ])|[^-_A-Za-z0-9 ]+";
        String str="hell/o h[ello i ,am good ";
        Map<String, Integer> result =service.count_freq(str,"");
        Map<String,Integer>  map=new HashMap<>();
        map.put("hell/o",1);
        map.put("h[ello",1);
        map.put("i",1);
        map.put(",am",1);
        map.put("good",1);

        assertEquals(map,result);
    }


}


