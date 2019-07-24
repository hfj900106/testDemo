package com.example.demo.io;


import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 字节输入流
 * @author hfj
 * @date 2019/4/4
 */
public class FileInputStreamTest2 {

    public static void main(String[] args) {
        try {
            @Cleanup
            FileInputStream inputStream = new FileInputStream("F://io_test//hfj1.txt");
            byte[] bytes = new byte[5];
            int length = bytes.length;
            System.out.println(length);
            int a = inputStream.read(bytes);
            while (a!=-1){
                // 注意new String有效读取到的的字节
                // abcdefg
                // hijklm
                // l
                System.out.print(new String(bytes,0,a) );
                // 否则 会受上次读取内容的影响
                //abcdefg
                //hijklm
                //llm
//                System.out.print(new String(bytes) );
                a = inputStream.read(bytes);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }
    }
}
