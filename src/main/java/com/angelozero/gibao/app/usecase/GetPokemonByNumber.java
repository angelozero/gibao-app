package com.angelozero.gibao.app.usecase;

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
public class GetPokemonByNumber {

    public static final int FIRST_SEASON = 150;
    private final PokemonApi pokemonApi;

    public String execute() {
        int pokemonRandomNumber = new Random().nextInt(FIRST_SEASON) + 1;

        try {
            String pokemon = pokemonApi.getImageByNumber(pokemonRandomNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();

            log.info(MessagesUtil.GET_POKEMON_BY_NUMBER_SUCCESS, pokemonRandomNumber);
            return pokemon;

        } catch (Exception ex) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(pokemonRandomNumber)
                    .message(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NUMBER_ERROR, ex.getMessage()))
                    .build());
        }
    }
}
