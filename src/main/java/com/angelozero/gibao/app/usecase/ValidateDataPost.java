package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.enums.UserDataPost;
import com.angelozero.gibao.app.util.MessagesUtil;
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
        Optional.ofNullable(dataPost)
                .map(data -> Optional.ofNullable(data.getAuthor())
                        .orElseThrow(() -> generateDataPostServiceException(dataPost)))
                .orElseThrow(() -> generateDataPostServiceException(dataPost));

        setSecretUser(dataPost);
    }

    private void setSecretUser(DataPost dataPost) {
        boolean isValidAuthor = UserDataPost.contains(dataPost.getAuthor().getName().toLowerCase());
        dataPost.setSecretUser(isValidAuthor);
        log.info(MessagesUtil.VALIDATE_DATA_POST_SUCCESS, isValidAuthor);
    }

    private DataPostServiceException generateDataPostServiceException(DataPost dataPost) {
        return new DataPostServiceException(Error.builder()
                .message(MessagesUtil.VALIDATE_DATA_POST_ERROR)
                .identifier(dataPost)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }
}
