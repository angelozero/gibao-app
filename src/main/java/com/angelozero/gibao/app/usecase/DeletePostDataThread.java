package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PostDataServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeletePostDataThread {

    private static final long MINUTES = 60000;
    private final DeletePostData deletePostData;

    public void execute(Long id) {
        try {
            log.info("[ THREAD INFO ] - Starting thread with {} minutes", MINUTES);
            Thread.sleep(MINUTES);

            log.info("[ THREAD INFO ] - Deleting post data");
            deletePostData.execute(id);

        } catch (InterruptedException ex) {
            log.error("[ THREAD ERROR ] - Error to delete post data");
            throw new PostDataServiceException(Error.builder()
                    .message(String.format("Error to delete a post data %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
