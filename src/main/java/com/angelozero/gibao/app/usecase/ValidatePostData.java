package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PostDataServiceException;
import com.angelozero.gibao.app.domain.PostData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ValidatePostData {

    public void execute(PostData postData) {
        log.info("[ INFO ] - Validating a post data");
        Optional.ofNullable(postData).map(data -> Optional.ofNullable(data.getAuthor()))
                .orElseThrow(() -> new PostDataServiceException(Error.builder()
                        .message("Error to validadte post data --- Invalid Post Data object")
                        .identifier(postData)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));

        setSecretUser(postData);
    }

    private void setSecretUser(PostData postData) {
        postData.setSecretUser(UserPostData.contains(postData.getAuthor().getName().toLowerCase()));
    }
}
