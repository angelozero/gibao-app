package com.angelozero.gibao.app.usecase.datapost;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.usecase.redis.RedisService;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.util.MessagesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteDataPost {

    private final DataPostGateway dataPostGateway;
    private final FindDataPost findDataPost;
    private final RedisService<List<DataPost>> redisService;

    public void execute(Long id) {
        try {
            if (findDataPost.execute(id) != null) {
                dataPostGateway.deleteById(id);
                log.info(MessagesUtil.DELETE_DATA_POST_SUCCESS, id);
            }
            updateRedisList(id);

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }

    private void updateRedisList(Long id) {
        try {
            List<Object> redisCache = redisService.findAll(RedisInfo.HASH_KEY_DATA_POST);

            if (!redisCache.isEmpty()) {
                ObjectMapper objMapper = new ObjectMapper();
                List<DataPost> dataPostRedisCacheList = objMapper.readValue(redisCache.get(0).toString(), objMapper.getTypeFactory().constructParametricType(List.class, DataPost.class));

                dataPostRedisCacheList.removeIf(dataPost -> dataPost.getId().longValue() == id.longValue());

                redisService.delete(RedisInfo.HASH_KEY_DATA_POST);
                redisService.save(RedisInfo.HASH_KEY_DATA_POST, UUID.randomUUID().toString(), dataPostRedisCacheList);
            }

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }
}