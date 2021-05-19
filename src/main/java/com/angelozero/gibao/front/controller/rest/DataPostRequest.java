package com.angelozero.gibao.front.controller.rest;

import lombok.*;


@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataPostRequest {

    private Long id;

    private AuthorRequest author;

    private String title;

    private String description;

}
