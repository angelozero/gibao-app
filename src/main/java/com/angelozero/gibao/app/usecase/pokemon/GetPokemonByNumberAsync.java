package com.angelozero.gibao.app.usecase.pokemon;

import com.angelozero.gibao.app.gateway.api.PokemonApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemonByNumberAsync {

    private final PokemonApi pokemonApi;

    @Async
    public CompletableFuture<String> executeAsync(int pokemonNumber) {
        return CompletableFuture.completedFuture(pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault());
    }
}
