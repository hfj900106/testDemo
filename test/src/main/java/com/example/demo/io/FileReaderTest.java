package com.example.demo.io;

import lombok.Cleanup;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author hfj
 * @date 2019/4/4
 */
public class FileReaderTest {
    public static void main(String[] args) {

        try {
            @Cleanup
            FileReader fileReader = new FileReader("F://io_test//hfj2.txt");
            char[] chars = new char[5];
            int read = fileReader.read(chars);
            while (read!=-1){
                System.out.print(new String(chars,0,read));
                read = fileReader.read(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
