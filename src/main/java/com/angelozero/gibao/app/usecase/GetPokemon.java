package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemon {

    public static final int FIRST_SEASON = 150;
    private final PokemonApi pokemonApi;

    public String execute() {
        int pokemonNumber = getRandomNumber();

        try {
            return pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();

        } catch (Exception ex) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(pokemonNumber)
                    .message("Failed to get pokemon image")
                    .build());
        }
    }

    private static int getRandomNumber() {
        return new Random().nextInt(FIRST_SEASON) + 1;
    }
}
