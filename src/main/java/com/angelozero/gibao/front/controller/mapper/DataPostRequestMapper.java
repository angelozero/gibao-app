package com.angelozero.gibao.front.controller.mapper;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.Author;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.util.MessagesUtil;
import com.angelozero.gibao.front.controller.rest.AuthorResponse;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;
import com.angelozero.gibao.front.controller.rest.DataPostResponse;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataPostRequestMapper {

    public static DataPost toDataPost(DataPostRequest dataPostRequest) {
        return Optional.ofNullable(dataPostRequest).map(post ->
                DataPost.builder()
                        .id(post.getId())
                        .author(Optional.ofNullable(post.getAuthor()).map(author ->
                                Author.builder().name(author.getName()).build())
                                .orElseThrow(() -> new MapperException(Error.builder()
                                        .status(HttpStatus.BAD_REQUEST)
                                        .identifier(dataPostRequest)
                                        .message(MessagesUtil.DATA_POST_REQUEST_MAPPER_ERROR_NULL_AUTHOR)
                                        .build())))
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(MessagesUtil.DATA_POST_REQUEST_MAPPER_ERROR_NULL_DATA_POST)
                        .build()));
    }

    public static DataPostResponse toDataPostResponse(DataPost dataPost) {
        return Optional.ofNullable(dataPost).map(post ->
                DataPostResponse.builder()
                        .id(post.getId())
                        .author(Optional.ofNullable(post.getAuthor())
                                .map(author ->
                                        AuthorResponse.builder()
                                                .name(author.getName())
                                                .build())
                                .orElse(AuthorResponse.builder().build()))
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .secretUser(post.getSecretUser())
                        .infoDate(post.getInfoDate())
                        .build())
                .orElse(DataPostResponse.builder().build());
    }

    public static List<DataPostResponse> toDataPostResponseList(List<DataPost> dataPostList) {
        return Optional.ofNullable(dataPostList).map(dataList ->
                dataList.stream().
                        map(DataPostRequestMapper::toDataPostResponse)
                        .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }
}
