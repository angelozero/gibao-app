package com.angelozero.gibao.app.usecase.pokemon;

import com.angelozero.gibao.app.gateway.api.PokemonApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
@AllArgsConstructor
@Async
public class GetPokemonByNumberAsync {

    private final PokemonApi pokemonApi;

    public String executeAsync(int pokemonNumber) {
        return CompletableFuture.supplyAsync(() -> pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault()).join();
    }
}
