package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.util.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FindDataPost {

    private final DataPostGateway dataPostGateway;

    public List<DataPost> execute() {
        log.info(MessageInfo.FIND_DATA_POST_LIST_INFO);
        return findAllPostsData();
    }

    public DataPost execute(Long id) {
        log.info(MessageInfo.FIND_DATA_POST_BY_ID_INFO, id);
        return findById(id);
    }

    private List<DataPost> findAllPostsData() {
        try {
            return dataPostGateway.findAll();

        } catch (Exception ex) {
            log.error(MessageInfo.FIND_DATA_POST_LIST_ERROR);
            throw new DataPostServiceException(Error.builder()
                    .message(String.format(MessageInfo.FIND_DATA_POST_LIST_ERROR_INFO, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }

    private DataPost findById(Long id) {
        try {
            return dataPostGateway.findById(id);

        } catch (Exception ex) {
            log.error(MessageInfo.FIND_DATA_POST_ID_ERROR, id);
            throw new DataPostServiceException(Error.builder()
                    .message(String.format(MessageInfo.FIND_DATA_POST_ID_ERROR_INFO, ex.getMessage()))
                    .identifier(ex)
                    .status(HttpStatus.BAD_REQUEST)
                    .build(), ex);
        }
    }
}
