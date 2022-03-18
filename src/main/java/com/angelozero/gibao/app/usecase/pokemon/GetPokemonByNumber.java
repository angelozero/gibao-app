package com.angelozero.gibao.app.usecase.pokemon;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.util.MessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemonByNumber {

    private final PokemonApi pokemonApi;

    public String execute(int pokemonNumber) {

        try {
            String pokemon = pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();

            log.info(MessagesUtil.GET_POKEMON_BY_NUMBER_SUCCESS, pokemonNumber);
            return pokemon;

        } catch (Exception ex) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(pokemonNumber)
                    .message(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NUMBER_ERROR, ex.getMessage()))
                    .build());
        }
    }
}
