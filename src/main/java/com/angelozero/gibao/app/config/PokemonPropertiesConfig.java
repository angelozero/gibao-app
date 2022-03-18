package com.angelozero.gibao.app.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class PokemonPropertiesConfig {

    @Value("${pokemon.first.season.names}")
    private List<String> seasonNamesList;

    @Value("${pokemon.first.season.count}")
    private Integer seasonCount;

}
