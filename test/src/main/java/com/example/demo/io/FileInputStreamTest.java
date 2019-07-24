package com.example.demo.io;


import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 字节输入流
 * @author hfj
 * @date 2019/4/4
 */
public class FileInputStreamTest {

    public static void main(String[] args) {
        try {
            @Cleanup
            FileInputStream inputStream = new FileInputStream("F://io_test//hfj1.txt");
            int a = inputStream.read();
            while (a!=-1){
                System.out.print((char) a);
                a = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }
    }
}
