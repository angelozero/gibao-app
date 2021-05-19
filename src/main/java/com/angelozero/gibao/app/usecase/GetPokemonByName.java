package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.util.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemonByName {

    private final PropertiesConfig propertiesConfig;
    private final PokemonApi pokemonApi;

    public String execute() {
        String pokemonName = getRandomName();

        try {
            log.info(MessageInfo.GET_POKEMON_BY_NAME_INFO, pokemonName);
            return pokemonApi.getImageByName(pokemonName).getSprites().getOther().getOfficialArtWork().getFrontDefault();

        } catch (Exception ex) {
            log.error(MessageInfo.GET_POKEMON_BY_NAME_ERROR);
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(pokemonName)
                    .message(String.format(MessageInfo.GET_POKEMON_BY_NAME_ERROR_INFO, ex.getMessage()))
                    .build(), ex);
        }
    }

    private String getRandomName() {
        return propertiesConfig.getPokemonList().get(new Random().nextInt(propertiesConfig.getPokemonList().size()));
    }
}
