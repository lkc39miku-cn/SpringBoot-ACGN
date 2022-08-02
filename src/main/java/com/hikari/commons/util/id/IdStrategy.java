package com.hikari.commons.util.id;

import com.hikari.commons.util.IdWorker;

public abstract class IdStrategy {


    private final IdWorker idWorker = new IdWorker(1, 1);

    /**
     * @description: 默认实现 雪花算法
     * @params: length 长度限制
     * @return: java.lang.Object
     */
    public Object product() {
        return idWorker.nextId();
    }

}