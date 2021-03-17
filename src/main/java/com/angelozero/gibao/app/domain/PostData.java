package com.angelozero.gibao.app.domain;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostData {

    private Long id;

    private Author author;

    private String title;

    private String description;

    private LocalDate date;

    private Boolean secretUser;
}
