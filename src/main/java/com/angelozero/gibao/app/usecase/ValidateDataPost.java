package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.enums.UserDataPost;
import com.angelozero.gibao.app.util.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ValidateDataPost {

    public void execute(DataPost dataPost) {
        log.info(MessageInfo.VALIDATE_DATA_POST);
        Optional.ofNullable(dataPost)
                .map(data -> Optional.ofNullable(data.getAuthor())
                        .orElseThrow(() -> generateDataPostServiceException(dataPost)))
                .orElseThrow(() -> generateDataPostServiceException(dataPost));

        setSecretUser(dataPost);
    }

    private void setSecretUser(DataPost dataPost) {
        dataPost.setSecretUser(UserDataPost.contains(dataPost.getAuthor().getName().toLowerCase()));
    }

    private DataPostServiceException generateDataPostServiceException(DataPost dataPost) {
        return new DataPostServiceException(Error.builder()
                .message(MessageInfo.VALIDADTE_DATA_POST_ERROR)
                .identifier(dataPost)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }
}
