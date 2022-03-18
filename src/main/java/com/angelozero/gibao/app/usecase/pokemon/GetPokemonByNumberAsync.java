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

    private static PokemonApi pokemonApi;

    public Future<String> executeAsync(int pokemonNumber) {
        return CompletableFuture.supplyAsync(getPokemonName(pokemonNumber));
    }

    private static String getPokemonName(int pokemonNumber) {
        return pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();

    }
}
