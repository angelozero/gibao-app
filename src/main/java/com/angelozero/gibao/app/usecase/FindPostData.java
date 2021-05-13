package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FindPostData {

    private final DataPostGateway dataPostGateway;

    public List<DataPost> execute() {
        log.info("[ INFO ] - Finding for a list of post data");
        return findAllPostsData();
    }

    public DataPost execute(Long id) {
        log.info("[ INFO ] - Find a post data by id {}", id);
        return findById(id);
    }

    private List<DataPost> findAllPostsData() {
        try {
            return dataPostGateway.findAll();

        } catch (Exception ex) {
            log.error("[ ERROR ] - Error to find a list of post data");
            throw new DataPostServiceException(Error.builder()
                    .message(String.format("Error to find a list of post data %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }

    private DataPost findById(Long id) {
        try {
            return dataPostGateway.findById(id);

        } catch (Exception ex) {
            log.error("[ ERROR ] - Error to find a post data by id {}", id);
            throw new DataPostServiceException(Error.builder()
                    .message(String.format("Error to find a post data %s", ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
