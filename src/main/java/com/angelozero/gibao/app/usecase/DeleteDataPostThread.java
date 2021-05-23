package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.util.MessagesUtil;
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
        validateDataPost.execute(dataPost);

        if (dataPost != null && dataPost.getSecretUser() != null && !dataPost.getSecretUser()) {
            startDeletePostDataThread(dataPost.getId());
        }
    }

    private void startDeletePostDataThread(Long id) {
        new Thread(() -> delete(id)).start();
    }

    private void delete(Long id) {
        try {
            log.info(MessagesUtil.DELETE_DATA_POST_THREAD_INFO_START_THREAD, propertiesConfig.getOneMinute());
            Thread.sleep(propertiesConfig.getOneMinute());

            deleteDataPost.execute(id);
            log.info(MessagesUtil.DELETE_DATA_POST_THREAD_SUCCESS, id);

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_THREAD_ERROR, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);

        }
    }
}
