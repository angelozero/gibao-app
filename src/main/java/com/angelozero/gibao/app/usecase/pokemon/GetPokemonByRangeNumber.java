package com.angelozero.gibao.app.usecase.pokemon;

import com.angelozero.gibao.app.config.PokemonPropertiesConfig;
import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.util.MessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemonByRangeNumber {

    private final PokemonPropertiesConfig pokemonPropertiesConfig;
    private final GetPokemonByNumberAsync getPokemonByNumberAsync;

    public List<String> execute(final int from, final int to) {

        validateRange(from, to);

        try {
            return IntStream.range(from == 0 ? 1 : from, to + 1)
                    .boxed()
                    .map(getPokemonByNumberAsync::executeAsync)
                    .collect(Collectors.toList())
                    .stream()
                    .map(CompletableFuture::join)
                    .distinct()
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(List.of(from, to))
                    .message(MessagesUtil.join(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR, ex.getMessage()))
                    .build());
        }
    }

    private void validateRange(int from, int to) {

        if (from > pokemonPropertiesConfig.getSeasonCount() || to > pokemonPropertiesConfig.getSeasonCount() || to < from) {
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(List.of(from, to))
                    .message(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR)
                    .build());
        }
    }
}
