package com.angelozero.gibao.app.usecase;

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
public class GetPokemonByNumber {

    public static final int FIRST_SEASON = 150;
    private final PokemonApi pokemonApi;

    public String execute() {
        int pokemonNumber = getRandomNumber();

        try {
            log.info(MessageInfo.GET_POKEMON_BY_NUMBER_INFO, pokemonNumber);
            return pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();

        } catch (Exception ex) {
            log.error(MessageInfo.GET_POKEMON_BY_NUMBER_ERROR);
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(pokemonNumber)
                    .message(String.format(MessageInfo.GET_POKEMON_BY_NUMBER_ERROR_INFO, ex.getMessage()))
                    .build(), ex);
        }
    }

    private static int getRandomNumber() {
        return new Random().nextInt(FIRST_SEASON) + 1;
    }
}
