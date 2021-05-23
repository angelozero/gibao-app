package com.angelozero.gibao.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("DataPost")
public class DataPost implements Serializable {

    public static final String REDIS_HASH = "DataPost";

    @JsonProperty("id")
    private Long id;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("infoDate")
    private String infoDate;

    @JsonProperty("secretUser")
    private Boolean secretUser;
}
