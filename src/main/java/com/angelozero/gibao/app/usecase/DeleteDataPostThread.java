package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.util.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteDataPostThread {

    private final PropertiesConfig propertiesConfig;
    private final DeleteDataPost deleteDataPost;
    private final ValidateDataPost validateDataPost;

    public void execute(DataPost dataPost) {

        log.info(MessageInfo.DELETE_DATA_POST_THREAD_INFO_VALIDATE);
        validateDataPost.execute(dataPost);

        if (dataPost != null && dataPost.getSecretUser() != null && !dataPost.getSecretUser()) {
            log.info(MessageInfo.DELETE_DATA_POST_THREAD_INFO_DELETE);
            startDeletePostDataThread(dataPost.getId());
        }
    }

    private void startDeletePostDataThread(Long id) {
        new Thread(() -> delete(id)).start();
    }

    private void delete(Long id) {
        try {
            log.info(MessageInfo.DELETE_DATA_POST_THREAD_INFO_START_THREAD, propertiesConfig.getOneMinute());
            Thread.sleep(propertiesConfig.getOneMinute());

            log.info(MessageInfo.DELETE_DATA_POST_THREAD_ERROR_DELETE);
            deleteDataPost.execute(id);

        } catch (Exception ex) {
            log.error(MessageInfo.DELETE_DATA_POST_THREAD_ERROR_DELETE);
            throw new DataPostServiceException(Error.builder()
                    .message(String.format(MessageInfo.DELETE_DATA_POST_THREAD_ERROR_DELETE_INFO, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);

        }
    }
}
