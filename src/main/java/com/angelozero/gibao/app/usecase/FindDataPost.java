package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.util.MessagesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class FindDataPost {

    private final DataPostGateway dataPostGateway;
    private final RedisService<List<DataPost>> redisService;

    public List<DataPost> execute() {
        return findAllPostsData();
    }

    public DataPost execute(Long id) {
        return findById(id);
    }

    private List<DataPost> findAllPostsData() {
        List<DataPost> dataPostList = getDataPostRedisCache();

        if (dataPostList != null) {
            return dataPostList;
        }

        try {
            dataPostList = dataPostGateway.findAll();
            redisService.save(RedisInfo.HASH_KEY_DATA_POST, UUID.randomUUID().toString(), dataPostList);

            log.info(MessagesUtil.FIND_DATA_POST_LIST_SUCCESS, dataPostList);
            return dataPostList;

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.FIND_DATA_POST_LIST_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }

    private DataPost findById(Long id) {
        try {
            DataPost dataPost = dataPostGateway.findById(id);

            log.info(MessagesUtil.FIND_DATA_POST_BY_ID_SUCCESS, dataPost);
            return dataPost;

        } catch (NoSuchElementException ex) {
            log.info(MessagesUtil.FIND_DATA_POST_BY_ID_NO_DATA_FOUND, id);
            return null;

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.FIND_DATA_POST_ID_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }

    private List<DataPost> getDataPostRedisCache() {
        List<Object> redisCache = redisService.findAll(RedisInfo.HASH_KEY_DATA_POST);

        try {
            if (!redisCache.isEmpty()) {
                ObjectMapper objMapper = new ObjectMapper();
                List<DataPost> dataPostRedisCacheList = objMapper.readValue(redisCache.get(0).toString(), objMapper.getTypeFactory().constructParametricType(List.class, DataPost.class));

                log.info(MessagesUtil.FIND_DATA_POST_LIST_SUCCESS_BY_REDIS, dataPostRedisCacheList);
                return dataPostRedisCacheList;
            }

            log.info(MessagesUtil.NO_DATA_POST_LIST_FOUND_BY_REDIS);
            return null;

        } catch (JsonProcessingException ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.FIND_DATA_POST_LIST_ERROR_BY_REDIS, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }
}
