package com.angelozero.gibao.front.controller.mapper;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.Author;
import com.angelozero.gibao.app.domain.PostData;
import com.angelozero.gibao.front.controller.rest.AuthorResponse;
import com.angelozero.gibao.front.controller.rest.PostDataRequest;
import com.angelozero.gibao.front.controller.rest.PostDataResponse;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class PostDataMapper {

    public static PostData toPostData(PostDataRequest postDataRequest) {
        return Optional.ofNullable(postDataRequest).map(post ->
                PostData.builder()
                        .id(post.getId())
                        .author(Optional.ofNullable(post.getAuthor()).map(author ->
                                Author.builder().name(author.getName()).build())
                                .orElseThrow(() -> new MapperException(Error.builder()
                                        .status(HttpStatus.BAD_REQUEST)
                                        .identifier(postDataRequest)
                                        .message("Error to validate a Author")
                                        .build())))
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .identifier(postDataRequest)
                        .message("Error to convert a post data request to post data")
                        .build()));
    }

    public static PostDataResponse toPostDataResponse(PostData postData) {
        return Optional.ofNullable(postData).map(post ->
                PostDataResponse.builder()
                        .id(post.getId())
                        .author(Optional.ofNullable(post.getAuthor()).map(author ->
                                AuthorResponse.builder().name(author.getName()).build())
                                .orElseThrow(() -> new MapperException(Error.builder()
                                        .status(HttpStatus.BAD_REQUEST)
                                        .identifier(postData)
                                        .message("Error to validate a Author Response")
                                        .build())))
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .identifier(postData)
                        .message("Error to convert a post data request to post data")
                        .build()));
    }
}
