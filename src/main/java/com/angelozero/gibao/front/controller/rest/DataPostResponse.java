package com.angelozero.gibao.front.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPostResponse {

    private Long id;

    private AuthorResponse author;

    private String title;

    private String description;

    private String infoDate;

    private Boolean secretUser;

}
