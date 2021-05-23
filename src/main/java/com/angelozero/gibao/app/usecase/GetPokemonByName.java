package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.util.MessagesUtil;
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
        try {
            String pokemonRandomName = propertiesConfig.getPokemonList().get(new Random().nextInt(propertiesConfig.getPokemonList().size()));
            String pokemonName = pokemonApi.getImageByName(pokemonRandomName).getSprites().getOther().getOfficialArtWork().getFrontDefault();

            log.info(MessagesUtil.GET_POKEMON_BY_NAME_SUCCESS, pokemonName);
            return pokemonName;

        } catch (Exception ex) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NAME_ERROR, ex.getMessage()))
                    .build(), ex);
        }
    }
}
