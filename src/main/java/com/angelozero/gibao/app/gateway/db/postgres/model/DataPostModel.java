package com.angelozero.gibao.app.gateway.db.postgres.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "data_post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataPostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "isSecretUser")
    private Boolean secretUser;
}
