package com.hzed.easyget.infrastructure.utils.id;

import lombok.extern.slf4j.Slf4j;

/**
 * ID生成器
 * @author guichang
 */

@Slf4j
public class IdentifierGenerator {
    private static SnowflakeIdWorker idWorker;

    static {

        try {
            long idWorkerId = Long.parseLong(System.getProperty("idWorkerId", "2"));
            long idDataCenterId = Long.parseLong(System.getProperty("idDataCenterId", "3"));
            idWorker = new SnowflakeIdWorker(idWorkerId, idDataCenterId);
        } catch (NumberFormatException e) {
            log.error(">>>>>>初始化ID生成器出错", e);
        }
    }

    /**
     * 获取下一个ID
     */

    public static long nextId() {
        return idWorker.nextId();

    }

    public static String nextSeqNo() {
        return nextId() + "hzed";
    }

}