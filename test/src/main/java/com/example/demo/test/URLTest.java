package com.example.demo.test;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author hfj
 * @date 2018/11/1
 */
public class URLTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("www.baidu.com");

        @Cleanup
        InputStream in =url.openStream();
        @Cleanup
        InputStreamReader isr = new InputStreamReader(in);
        @Cleanup
        BufferedReader bufr = new BufferedReader(isr);
        String str;
        while ((str = bufr.readLine()) != null) {
            System.out.println(str);
        }
    }

}
