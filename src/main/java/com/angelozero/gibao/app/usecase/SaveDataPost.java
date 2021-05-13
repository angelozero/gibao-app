package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
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
            log.info("[ INFO ] - Saving post data");
            DataPost dataPostSaved = dataPostGateway.save(dataPost);

            log.info("[ INFO ] - Validating and deleting a post data if wasn't created by a secret user");
            deleteDataPostThread.execute(dataPostSaved);

        } catch (Exception ex) {
            log.error("[ ERROR ] - Error to save post data");
            throw new DataPostServiceException(Error.builder()
                    .message(String.format("Error to save a post data %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }


}
