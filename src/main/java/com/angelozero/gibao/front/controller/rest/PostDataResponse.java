package com.angelozero.gibao.front.controller.rest;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDataResponse {

    private Long id;

    private AuthorResponse author;

    private String title;

    private String description;

    private LocalDate date;

    private Boolean secretUser;

}
