package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.exception.RedisServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class RedisServiceTest {

    RedisTemplate template = Mockito.mock(RedisTemplate.class);
    HashOperations hashOperations = Mockito.mock(HashOperations.class);


    @Test
    public void shouldFindAllValuesInRedisWithSuccess() {
        Mockito.when(template.opsForHash()).thenReturn(hashOperations);
        Mockito.when(hashOperations.values(RedisInfo.HASH_KEY_DATA_POST.getKey())).thenReturn(Collections.singletonList("test valid redis list"));

        RedisService<String> redisService = new RedisService<String>(template);
        List<Object> redisCacheList = redisService.findAll(RedisInfo.HASH_KEY_DATA_POST);

        assertNotNull(redisCacheList);
        assertFalse(redisCacheList.isEmpty());
        assertEquals("test valid redis list", redisCacheList.get(0).toString());

    }

    @Test
    public void shouldThrowAnExceptionWhenFindAll() {
        Mockito.when(template.opsForHash()).thenThrow(new RuntimeException("Error find all - Redis"));

        RedisService<String> redisService = new RedisService<String>(template);
        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.findAll(RedisInfo.HASH_KEY_DATA_POST));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_FIND_ALL, "Error find all - Redis"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldFindAValueInRedisWithSuccess() {
        Mockito.when(template.opsForHash()).thenReturn(hashOperations);
        Mockito.when(hashOperations.get(DataPost.REDIS_HASH, RedisInfo.HASH_KEY_DATA_POST.getKey())).thenReturn(("test valid redis"));

        RedisService<String> redisService = new RedisService<String>(template);
        Object redisCache = redisService.find(DataPost.REDIS_HASH, RedisInfo.HASH_KEY_DATA_POST);

        assertNotNull(redisCache);
        assertEquals("test valid redis", redisCache.toString());

    }

    @Test
    public void shouldThrowAnExceptionWhenFindAData() {
        Mockito.when(template.opsForHash()).thenThrow(new RuntimeException("Error find data - Redis"));

        RedisService<String> redisService = new RedisService<String>(template);
        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.find(DataPost.REDIS_HASH, RedisInfo.HASH_KEY_DATA_POST));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_FIND, "Error find data - Redis"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldDeleteRedisCacheSuccess() {
        Mockito.when(template.delete(RedisInfo.HASH_KEY_DATA_POST)).thenReturn(Boolean.TRUE);
        RedisService<String> redisService = new RedisService<String>(template);
        redisService.delete(RedisInfo.HASH_KEY_DATA_POST);

        Mockito.verify(template, Mockito.times(1)).delete(RedisInfo.HASH_KEY_DATA_POST.getKey());
    }

    @Test
    public void shouldThrowAnExceptionWhenDeleteAData() {

        RedisService<String> redisService = new RedisService<String>(null);
        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.delete(RedisInfo.HASH_KEY_DATA_POST));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_DELETE, StringUtils.EMPTY), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldSaveRedisCacheSuccess() {
        Mockito.when(template.opsForHash()).thenReturn(hashOperations);
        Mockito.doNothing().when(hashOperations).put(anyString(), anyString(), anyString());
        RedisService<String> redisService = new RedisService<String>(template);
        redisService.save(RedisInfo.HASH_KEY_DATA_POST, "id", "save redis data test");

        Mockito.verify(hashOperations, Mockito.times(1)).put(anyString(), anyString(), anyString());
    }

    @Test
    public void shouldThrowAnExceptionWhenSaveAData() {

        Mockito.when(template.opsForHash()).thenThrow(new RuntimeException("Error save data - Redis"));

        RedisService<String> redisService = new RedisService<String>(template);
        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.save(RedisInfo.HASH_KEY_DATA_POST, "id", "save redis data test"));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_SAVE, "Error save data - Redis"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
