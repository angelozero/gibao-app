package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PostDataServiceException;
import com.angelozero.gibao.app.gateway.PostDataGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeletePostData {

    PostDataGateway postDataGateway;

    public void execute(Long id) {
        try {
            //TODO verificar antes de deletar se o post existe
            log.info("[ INFO ] - Deleting post data {}", id);
            postDataGateway.delete(id);

        } catch (Exception ex) {
            log.error("[ ERROR ] - Error to delete post data");
            throw new PostDataServiceException(Error.builder()
                    .message(String.format("Error to delete a post data %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
