package com.angelozero.gibao.front.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPostResponse {

    private Long id;

    private AuthorResponse author;

    private String title;

    private String description;

    private LocalDate date;

    private Boolean secretUser;

}
