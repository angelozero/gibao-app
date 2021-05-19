package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.util.MessageInfo;
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

    public void execute(DataPost dataPost) {
        validateDataPost.execute(dataPost);

        try {
            log.info(MessageInfo.SAVE_DATA_POST_INFO);
            DataPost dataPostSaved = dataPostGateway.save(dataPost);

            log.info(MessageInfo.SAVE_DATA_POST_INFO_DELETE_IF_NOT_SECRET_USER);
            deleteDataPostThread.execute(dataPostSaved);

        } catch (Exception ex) {
            log.error(MessageInfo.SAVE_DATA_POST_ERROR);
            throw new DataPostServiceException(Error.builder()
                    .message(String.format(MessageInfo.SAVE_DATA_POST_ERROR_INFO, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
