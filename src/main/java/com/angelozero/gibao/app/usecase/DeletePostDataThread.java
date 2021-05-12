package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PostDataServiceException;
import com.angelozero.gibao.app.domain.PostData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeletePostDataThread {

    private static final long ONE_MINUTE = 60000;
    private final DeletePostData deletePostData;
    private final ValidatePostData validatePostData;

    public void execute(PostData postData) {

        log.info("[ THREAD INFO ] - Validating post data to delete by thread");
        validatePostData.execute(postData);

        if (!postData.getSecretUser()) {
            log.info("[ THREAD INFO ] - Deleting post data by thread");
            startDeletePostDataThread(postData.getId());
        }
    }

    private void startDeletePostDataThread(Long id) {
        new Thread(() -> delete(id)).start();
    }

    private void delete(Long id) {
        try {
            log.info("[ THREAD INFO ] - Starting thread with {} minutes", ONE_MINUTE);
            Thread.sleep(ONE_MINUTE);

            log.info("[ THREAD INFO ] - Deleting post data by thread");
            deletePostData.execute(id);

        } catch (InterruptedException ex) {
            log.error("[ THREAD ERROR ] - Error to delete post data by thread");
            throw new PostDataServiceException(Error.builder()
                    .message(String.format("Error to delete a post data by thread %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
