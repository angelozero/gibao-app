package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.util.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteDataPost {

    private final DataPostGateway dataPostGateway;
    private final FindDataPost findDataPost;

    public void execute(Long id) {
        try {
            log.info(MessageInfo.DELETE_DATA_POST_LOG_INFO_CHECK, id);
            if (findDataPost.execute(id) != null) {

                log.info(MessageInfo.DELETE_DATA_POST_LOG_INFO_DELETE, id);
                dataPostGateway.deleteById(id);
            }

        } catch (Exception ex) {
            log.error(MessageInfo.DELETE_DATA_POST_LOG_ERROR_DELETE);
            throw new DataPostServiceException(Error.builder()
                    .message(String.format(MessageInfo.DELETE_DATA_POST_FAIL, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
