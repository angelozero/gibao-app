package com.angelozero.gibao.app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @JsonProperty("name")
    private String name;
}
