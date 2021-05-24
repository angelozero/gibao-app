package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.RedisServiceException;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.util.MessagesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RedisService<T> {

    private final RedisTemplate<String, Object> template;

    public List<Object> findAll(RedisInfo redisInfo) {
        try {
            List<Object> redisCacheList = template.opsForHash().values(redisInfo.getKey());

            log.info(MessagesUtil.REDIS_SERVICE_SUCCESS_TO_FIND_ALL, redisCacheList);
            return redisCacheList;

        } catch (Exception ex) {
            throw new RedisServiceException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(redisInfo)
                    .message(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_FIND_ALL, ex.getMessage()))
                    .build());
        }
    }

    public Object find(String classRedisHash, RedisInfo redisInfo) {
        try {
            Object redisCache = template.opsForHash().get(classRedisHash, redisInfo.getKey());

            log.info(MessagesUtil.REDIS_SERVICE_SUCCESS_TO_FIND, redisCache);
            return redisCache;

        } catch (Exception ex) {
            throw new RedisServiceException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(Arrays.asList(classRedisHash, redisInfo))
                    .message(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_FIND, ex.getMessage()))
                    .build());
        }
    }

    public void delete(RedisInfo redisInfo) {
        try {
            template.delete(redisInfo.getKey());
            log.info(MessagesUtil.REDIS_SERVICE_SUCCESS_TO_DELETE, redisInfo);

        } catch (Exception ex) {
            throw new RedisServiceException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(redisInfo)
                    .message(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_DELETE, ex.getMessage()))
                    .build());
        }
    }

    public void save(RedisInfo redisInfo, String id, T obj) {
        try {
            template.opsForHash().put(redisInfo.getKey(), id, new ObjectMapper().writeValueAsString(obj));
            log.info(MessagesUtil.REDIS_SERVICE_SUCCESS_TO_SAVE, redisInfo, id, obj);

        } catch (Exception ex) {
            throw new RedisServiceException(Error.builder()
                    .identifier(Arrays.asList(redisInfo, obj))
                    .status(HttpStatus.BAD_REQUEST)
                    .message(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_SAVE, ex.getMessage()))
                    .build());
        }
    }
}
