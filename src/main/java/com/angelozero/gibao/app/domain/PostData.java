package com.angelozero.gibao.app.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class PostData {

    private final Long id;

    private final Author author;

    private final String title;

    private final String description;
}
