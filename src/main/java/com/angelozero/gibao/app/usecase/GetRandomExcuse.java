package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class GetRandomExcuse {

    private final FindDataPost findDataPost;

    public String execute() {

        List<DataPost> allPosts = findDataPost.execute();

        try {
            Optional.ofNullable(allPosts).orElseThrow(() -> new DataPostServiceException(Error.builder()
                    .message("Não houve retorno do banco")
                    .status(HttpStatus.BAD_REQUEST)
                    .build()));

            return !allPosts.isEmpty() ? Collections.singletonList(allPosts.get(new Random().nextInt(allPosts.size()))).get(0).getDescription() : StringUtils.EMPTY;

        } catch (Exception ex) {
            throw new DataPostServiceException(Error.builder()
                    .message("Erro ao recuperar dados " + ex.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }
}
