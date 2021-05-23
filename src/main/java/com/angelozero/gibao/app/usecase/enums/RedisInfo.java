package com.angelozero.gibao.app.usecase.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RedisInfo {

    HASH_KEY_DATA_POST("DataPost");

    private final String key;

    RedisInfo(String key) {
        this.key = key;
    }

    public static boolean contains(String hashKey) {
        return Arrays.stream(RedisInfo.values())
                .anyMatch(redisInfo -> redisInfo.getKey().equals(hashKey.toLowerCase()));
    }
}
