package com.angelozero.gibao.app.usecase.datapost;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.usecase.redis.RedisService;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.util.MessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SaveDataPost {

    private final DataPostGateway dataPostGateway;
    private final ValidateDataPost validateDataPost;
    private final DeleteDataPostThread deleteDataPostThread;
    private final RedisService<DataPost> redisService;

    public void execute(DataPost dataPost) {
        validateDataPost.execute(dataPost);

        try {
            DataPost dataPostSaved = dataPostGateway.save(dataPost);
            redisService.delete(RedisInfo.HASH_KEY_DATA_POST);
            log.info(MessagesUtil.SAVE_DATA_POST_SUCCESS, dataPostSaved);

            deleteDataPostThread.execute(dataPostSaved);

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.SAVE_DATA_POST_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }
}
