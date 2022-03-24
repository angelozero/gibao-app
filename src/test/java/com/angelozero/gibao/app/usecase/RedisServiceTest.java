package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.exception.RedisServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.usecase.redis.RedisService;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@MockitoSettings
class RedisServiceTest {

    @Mock
    private RedisTemplate template;

    @Mock
    private HashOperations hashOperations;

    @InjectMocks
    private RedisService<String> redisService;

    @Test
    @DisplayName("Should find all values in redis with success")
    void shouldFindAllValuesInRedisWithSuccess() {

        Mockito.when(template.opsForHash()).thenReturn(hashOperations);
        Mockito.when(hashOperations.values(RedisInfo.HASH_KEY_DATA_POST.getKey())).thenReturn(Collections.singletonList("test valid redis list"));

        List<Object> redisCacheList = redisService.findAll(RedisInfo.HASH_KEY_DATA_POST);

        assertNotNull(redisCacheList);
        assertFalse(redisCacheList.isEmpty());
        assertEquals("test valid redis list", redisCacheList.get(0).toString());
    }

    @Test
    @DisplayName("Should thrown an exception when find all values in redis")
    void shouldThrowAnExceptionWhenFindAll() {

        Mockito.when(template.opsForHash()).thenThrow(new RuntimeException("Error find all - Redis"));

        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.findAll(RedisInfo.HASH_KEY_DATA_POST));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_FIND_ALL, "Error find all - Redis"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    @DisplayName("Should find a value in redis with success")
    void shouldFindAValueInRedisWithSuccess() {

        Mockito.when(template.opsForHash()).thenReturn(hashOperations);
        Mockito.when(hashOperations.get(DataPost.REDIS_HASH, RedisInfo.HASH_KEY_DATA_POST.getKey())).thenReturn(("test valid redis"));

        Object redisCache = redisService.find(DataPost.REDIS_HASH, RedisInfo.HASH_KEY_DATA_POST);

        assertNotNull(redisCache);
        assertEquals("test valid redis", redisCache.toString());

    }

    @Test
    @DisplayName("Should thrown an exception when find a data in redis")
    void shouldThrowAnExceptionWhenFindAData() {

        Mockito.when(template.opsForHash()).thenThrow(new RuntimeException("Error find data - Redis"));

        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.find(DataPost.REDIS_HASH, RedisInfo.HASH_KEY_DATA_POST));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_FIND, "Error find data - Redis"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    @DisplayName("Should delete redis cache with success")
    void shouldDeleteRedisCacheSuccess() {

        Mockito.lenient().when(template.delete(RedisInfo.HASH_KEY_DATA_POST)).thenReturn(Boolean.TRUE);

        redisService.delete(RedisInfo.HASH_KEY_DATA_POST);

        Mockito.verify(template, Mockito.times(1)).delete(RedisInfo.HASH_KEY_DATA_POST.getKey());
    }

    @Test
    @DisplayName("Should thrown an exception when delete redis data")
    void shouldThrowAnExceptionWhenDeleteAData() {

        Mockito.when(template.delete(anyString())).thenThrow(new RuntimeException("Error Redis Test"));

        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.delete(RedisInfo.HASH_KEY_DATA_POST));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_DELETE, "Error Redis Test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    @DisplayName("Should save redis data with success")
    void shouldSaveRedisCacheSuccess() {

        Mockito.when(template.opsForHash()).thenReturn(hashOperations);
        Mockito.doNothing().when(hashOperations).put(anyString(), anyString(), anyString());

        redisService.save(RedisInfo.HASH_KEY_DATA_POST, "id", "save redis data test");

        Mockito.verify(hashOperations, Mockito.times(1)).put(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should thrown an exception when save redis data")
    void shouldThrowAnExceptionWhenSaveAData() {

        Mockito.when(template.opsForHash()).thenThrow(new RuntimeException("Error save data - Redis"));

        RedisServiceException exception = assertThrows(RedisServiceException.class, () -> redisService.save(RedisInfo.HASH_KEY_DATA_POST, "id", "save redis data test"));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.REDIS_SERVICE_ERRO_TO_SAVE, "Error save data - Redis"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
