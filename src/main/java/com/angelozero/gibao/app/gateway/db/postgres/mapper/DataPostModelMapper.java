package com.angelozero.gibao.app.gateway.db.postgres.mapper;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.Author;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.util.MessageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataPostModelMapper {

    public static DataPostModel toPostDataModel(DataPost dataPost) {
        return Optional.ofNullable(dataPost).map(post ->
                DataPostModel.builder()
                        .date(LocalDate.now())
                        .id(post.getId())
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .secretUser(post.getSecretUser() != null ? post.getSecretUser() : false)
                        .author(Optional.ofNullable(post.getAuthor())
                                .map(Author::getName)
                                .orElse(StringUtils.EMPTY))
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .message(MessageInfo.DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_DATA_MODEL)
                        .identifier(dataPost)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));
    }

    public static DataPost toPostData(DataPostModel dataPostModel) {
        return Optional.ofNullable(dataPostModel).map(post ->
                DataPost.builder()
                        .id(post.getId())
                        .author(Author.builder().name(post.getAuthor()).build())
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .date(post.getDate())
                        .secretUser(post.getSecretUser() != null ? post.getSecretUser() : false)
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .message(MessageInfo.DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_POST_DATA)
                        .identifier(dataPostModel)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));
    }

    public static List<DataPost> toPostDataList(List<DataPostModel> dataPostModelList) {
        return Optional.ofNullable(dataPostModelList).map(postDataList ->
                postDataList.stream()
                        .map(DataPostModelMapper::toPostData)
                        .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }
}
