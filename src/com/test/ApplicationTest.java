package com.test;

import com.main.Application;
import org.junit.Test;
import static org.junit.Assert.*;



public class ApplicationTest {
    final String inputFolder = "E:\\files";
    final String outputFolder= "E:\\output";
    final String regex = "[ ](?=[ ])|[^-_A-Za-z0-9 ]+";

    final String inputFile="E:\\files\\text.txt";

    Application application=new Application();

//    @After
//    public void deleteOutputFile()
//    {
//        Path path = Paths.get(inputFile);
//        String fileName=path.getFileName().toString();
//        File output=new File(outputDirectory+"\\"+fileName);
//        output.delete();
//    }

    @Test
    public void firstPart_With_Valid_Input(){
       application.firstPart(inputFolder,outputFolder,regex);
    }

    @Test()
    public void firstPart_With_InValid_Input(){
        application.firstPart(inputFolder+"abd",outputFolder+"nmc",regex+"na");
    }

    @Test()
    public void firstPart_With_Null(){
        application.firstPart(null,null,null);
    }

    @Test
    public void firstPartSingleFile_Valid_Input(){
        boolean result=application.firstPartSingleFile(inputFile,outputFolder,regex);
        assertTrue(result);
    }

    @Test()
    public void firstPartSingleFile_InValid_Input(){

        boolean result=application.firstPartSingleFile(inputFile+"hjasj",outputFolder+"kgakg",null);

        assertFalse(result);
    }

    @Test()
    public void firstPartSingleFile_Null(){

        boolean result=application.firstPartSingleFile(null,null,null);

        assertFalse(result);
    }

    @Test
    public void secondPart_valid_input(){
       boolean r= application.secondPart(inputFile,outputFolder,regex);
       assertTrue(r);
    }

    @Test
    public void secondPart_Invalid_input(){
        application.secondPart(inputFile+"jg","gk"+outputFolder,regex+"jkg");
    }

    @Test()
    public void secondPart_Invalid_input2(){
        application.secondPart(inputFile,"gk"+outputFolder,regex+"jkg");
    }

    @Test()
    public void secondPart_Invalid_input3(){
        application.secondPart("kjgk"+inputFile,outputFolder,regex+"jkg");
    }

    @Test()
    public void secondPart_Null1(){
        application.secondPart(null,null,null);
    }


}
