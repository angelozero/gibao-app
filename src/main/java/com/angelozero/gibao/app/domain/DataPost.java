package com.angelozero.gibao.app.domain;

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
@RedisHash("DataPost")
public class DataPost implements Serializable {

    private Long id;

    private Author author;

    private String title;

    private String description;

    private LocalDate date;

    private Boolean secretUser;
}
