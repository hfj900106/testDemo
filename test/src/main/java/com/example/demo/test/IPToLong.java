package com.example.demo.test;

/**
 * @author hfj
 * @date 2019-11-05
 */
public class IPToLong {
    public static void main(String[] args) {
        // 建议用整型存储IP
        String ip = "172.168.1.3";
        String[] ips = ip.split("\\.");
        System.out.println(Long.parseLong(ips[0]) << 24 + Long.parseLong(ips[1]) << 16 + Long.parseLong(ips[2]) << 8 + Long.parseLong(ips[3]));

    }
}
