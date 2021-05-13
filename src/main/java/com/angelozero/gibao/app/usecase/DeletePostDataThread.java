package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeletePostDataThread {

    private final PropertiesConfig propertiesConfig;
    private final DeletePostData deletePostData;
    private final ValidatePostData validatePostData;

    public void execute(DataPost dataPost) {

        log.info("[ THREAD ] - Validating post data to delete by thread");
        validatePostData.execute(dataPost);

        if (dataPost != null && dataPost.getSecretUser() != null && !dataPost.getSecretUser()) {
            log.info("[ THREAD ] - Deleting post data by thread");
            startDeletePostDataThread(dataPost.getId());
        }
    }

    private void startDeletePostDataThread(Long id) {
        new Thread(() -> delete(id)).start();
    }

    private void delete(Long id) {
        try {
            log.info("[ THREAD ] - Starting thread with {} minutes", propertiesConfig.getOneMinute());
            Thread.sleep(propertiesConfig.getOneMinute());

            log.info("[ THREAD ] - Deleting post data by thread");
            deletePostData.execute(id);

        } catch (Exception ex) {
            log.error("[ THREAD ] - Error to delete post data by thread");
            throw new DataPostServiceException(Error.builder()
                    .message(String.format("Error to delete a post data by thread %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);

        }
    }
}
