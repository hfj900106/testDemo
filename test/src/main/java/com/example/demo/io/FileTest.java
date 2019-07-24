package com.example.demo.io;

import java.io.File;
import java.io.IOException;

/**
 * 文件类
 * @author hfj
 * @date 2019/4/4
 */
public class FileTest {

    public static void main(String[] args) {

        File file = new File("F://io_test");
        if(file.mkdir()){
            File file1 = new File("F://io_test//hfj1.txt");
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件创建失败");
            }
        }
    }

}
