package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
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
        log.info("[ INFO ] - Validating a post data");
        Optional.ofNullable(dataPost).map(data -> Optional.ofNullable(data.getAuthor()))
                .orElseThrow(() -> new DataPostServiceException(Error.builder()
                        .message("Error to validadte post data --- Invalid Post Data object")
                        .identifier(dataPost)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));

        setSecretUser(dataPost);
    }

    private void setSecretUser(DataPost dataPost) {
        dataPost.setSecretUser(UserDataPost.contains(dataPost.getAuthor().getName().toLowerCase()));
    }
}
