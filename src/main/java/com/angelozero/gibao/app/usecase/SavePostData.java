package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PostDataServiceException;
import com.angelozero.gibao.app.domain.PostData;
import com.angelozero.gibao.app.gateway.PostDataGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SavePostData {

    PostDataGateway postDataGateway;
    ValidatePostData validatePostData;

    public void execute(PostData postData) {

        validatePostData.execute(postData);

        try {
            log.info("[ INFO ] - Saving post data");
            postDataGateway.save(postData);

        } catch (Exception ex) {
            log.error("[ ERROR ] - Error to save post data");
            throw new PostDataServiceException(Error.builder()
                    .message(String.format("Error to save a post data %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
