package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.util.MessagesUtil;
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
            if (findDataPost.execute(id) != null) {
                dataPostGateway.deleteById(id);
                log.info(MessagesUtil.DELETE_DATA_POST_SUCCESS, id);
            }

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
