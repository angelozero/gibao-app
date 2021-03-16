package com.angelozero.gibao.app.gateway.db.postgres.mapper;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.Author;
import com.angelozero.gibao.app.domain.PostData;
import com.angelozero.gibao.app.gateway.db.postgres.model.PostDataModel;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostDataModelMapper {

    public static PostDataModel toPostDataModel(PostData postData) {
        return Optional.ofNullable(postData).map(post ->
                PostDataModel.builder()
                        .date(LocalDate.now())
                        .id(post.getId())
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .author(Optional.ofNullable(post.getAuthor())
                                .map(Author::getName)
                                .orElseThrow(() -> new MapperException(Error.builder()
                                        .message("Error to validate Author for model")
                                        .identifier(postData)
                                        .status(HttpStatus.BAD_REQUEST)
                                        .build())))
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .message("Error to convert post data to a post data model")
                        .identifier(postData)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));
    }

    public static PostData toPostData(PostDataModel postDataModel) {
        return Optional.ofNullable(postDataModel).map(post ->
                PostData.builder()
                        .id(post.getId())
                        .author(Author.builder().name(post.getAuthor()).build())
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .date(post.getDate())
                        .build())
                .orElseThrow(() -> new MapperException(Error.builder()
                        .message("Error to convert post data to a post data model")
                        .identifier(postDataModel)
                        .status(HttpStatus.BAD_REQUEST)
                        .build()));
    }

    public static List<PostData> toPostDataList(List<PostDataModel> postDataModelList) {
        return Optional.ofNullable(postDataModelList).map(postDataList ->
                postDataList.stream().map(post ->
                        PostData.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .description(post.getDescription())
                                .date(post.getDate())
                                .build()
                ).collect(Collectors.toList())

        ).orElse(Collections.emptyList());
    }
}
