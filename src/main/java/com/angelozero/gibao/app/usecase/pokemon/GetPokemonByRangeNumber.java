package com.angelozero.gibao.app.usecase.pokemon;

import com.angelozero.gibao.app.config.PokemonPropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.util.MessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemonByRangeNumber {

    private final PokemonPropertiesConfig pokemonPropertiesConfig;
    private final GetPokemonByNumberAsync getPokemonByNumberAsync;

    public List<String> execute(int from, int to) {

        validadteRange(from, to);

        try {
            List<CompletableFuture<Future<String>>> pokemons = IntStream.range(0, 5).boxed().map(pokemonNumber -> CompletableFuture.supplyAsync(getPokemonByNumberAsync.executeAsync(pokemonNumber))).collect(Collectors.toList());

            log.info(MessagesUtil.GET_POKEMONS_BY_RANGER_NUMBER_SUCCESS, pokemons);
            return pokemons.stream().map(CompletableFuture::join).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(List.of(from, to))
                    .message(MessagesUtil.join(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR, ex.getMessage()))
                    .build());
        }
    }

    private void validadteRange(int from, int to) {
        if (from > pokemonPropertiesConfig.getSeasonCount() || to > pokemonPropertiesConfig.getSeasonCount() || to > from) {
            throw generateException(from, to);
        }
    }

    private PokemonApiException generateException(int from, int to) {
        return new PokemonApiException(Error.builder()
                .status(HttpStatus.BAD_REQUEST)
                .identifier(List.of(from, to))
                .message(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR)
                .build());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<CompletableFuture<Future<String>>> teste = IntStream.range(0, 5).boxed().map(pokemonNumber -> CompletableFuture.supplyAsync(GetPokemonByRangeNumber::getPokemonByNumberAsync)).collect(Collectors.toList());
        teste.stream().map(CompletableFuture::join).collect(Collectors.toList()).forEach(System.out::println);

    }

    public static Future<String> getPokemonByNumberAsync() {
        return CompletableFuture.supplyAsync(GetPokemonByRangeNumber::getName);
    }

    private static String getName() {
        return "Resultado ok";
    }
}
