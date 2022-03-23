package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.PokemonPropertiesConfig;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByNumberAsync;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonsByRangeNumber;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@MockitoSettings
public class GetPokemonsByRangeNumberTest {

    public static final int SEASON_COUNT = 151;

    private static final int FROM = 0;
    private static final int TO = 3;

    private static final int FROM_ERROR = 150;
    private static final int TO_ERROR = 0;

    private static final int FROM_ERROR_SEASON_COUNT = 152;
    private static final int TO_ERROR_SEASON_COUNT = 152;


    @Mock
    private PokemonPropertiesConfig pokemonPropertiesConfig;

    @Mock
    private GetPokemonByNumberAsync getPokemonByNumberAsync;

    @InjectMocks
    private GetPokemonsByRangeNumber getPokemonsByRangeNumber;


    @Test
    @DisplayName("Should execute with success async call to get pokemon list")
    public void shouldGetAListOfPokemonsAsyncWIthSuccess() {

        Mockito.when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(SEASON_COUNT);
        Mockito.when(getPokemonByNumberAsync.executeAsync(1)).thenReturn(CompletableFuture.completedFuture("pikachu"));
        Mockito.when(getPokemonByNumberAsync.executeAsync(2)).thenReturn(CompletableFuture.completedFuture("charmander"));
        Mockito.when(getPokemonByNumberAsync.executeAsync(3)).thenReturn(CompletableFuture.completedFuture("squirtle"));

        List<String> listOfPokemons = getPokemonsByRangeNumber.execute(FROM, TO);

        assertNotNull(listOfPokemons);
        assertFalse(listOfPokemons.isEmpty());
        assertEquals(3, listOfPokemons.size());
        assertEquals("pikachu", listOfPokemons.get(0));
        assertEquals("charmander", listOfPokemons.get(1));
        assertEquals("squirtle", listOfPokemons.get(2));
    }

    @Test
    @DisplayName("Should throw an error when executing an async call to get pokemon list")
    public void shouldGenerateAnExceptionToGetThePokemonAsyncList() {

        Mockito.when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(SEASON_COUNT);
        Mockito.when(getPokemonByNumberAsync.executeAsync(anyInt())).thenThrow(new RuntimeException("Error to execute async call"));

        PokemonApiException exception = assertThrows(PokemonApiException.class, () -> getPokemonsByRangeNumber.execute(FROM, TO));

        assertNotNull(exception);
        assertNotNull(exception.getError());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(List.of(FROM, TO), exception.getError().getIdentifier());
        assertEquals(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR + " - Error to execute async call", exception.getError().getMessage());
    }

    @Test
    @DisplayName("Should throw an error when call the service with FROM bigger than SEASON_COUNT")
    public void shouldGenerateAnExceptionToGetThePokemonAsyncListWithFromBiggerThanSeasonCount() {

        Mockito.when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(SEASON_COUNT);

        PokemonApiException exception = assertThrows(PokemonApiException.class, () -> getPokemonsByRangeNumber.execute(FROM_ERROR_SEASON_COUNT, TO));

        assertNotNull(exception);
        assertNotNull(exception.getError());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(List.of(FROM_ERROR_SEASON_COUNT, TO), exception.getError().getIdentifier());
        assertEquals(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR, exception.getError().getMessage());
    }

    @Test
    @DisplayName("Should throw an error when call the service with TO bigger than SEASON_COUNT")
    public void shouldGenerateAnExceptionToGetThePokemonAsyncListWithToBiggerThanSeasonCount() {

        Mockito.when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(SEASON_COUNT);

        PokemonApiException exception = assertThrows(PokemonApiException.class, () -> getPokemonsByRangeNumber.execute(FROM, TO_ERROR_SEASON_COUNT));

        assertNotNull(exception);
        assertNotNull(exception.getError());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(List.of(FROM, TO_ERROR_SEASON_COUNT), exception.getError().getIdentifier());
        assertEquals(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR, exception.getError().getMessage());
    }

    @Test
    @DisplayName("Should throw an error when call the service with FROM bigger than TO")
    public void shouldGenerateAnExceptionToGetThePokemonAsyncListWithFromBiggerThanTo() {

        Mockito.when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(SEASON_COUNT);

        PokemonApiException exception = assertThrows(PokemonApiException.class, () -> getPokemonsByRangeNumber.execute(FROM_ERROR, TO_ERROR));

        assertNotNull(exception);
        assertNotNull(exception.getError());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(List.of(FROM_ERROR, TO_ERROR), exception.getError().getIdentifier());
        assertEquals(MessagesUtil.GET_POKEMONS_BY_RANGE_NUMBER_ERROR, exception.getError().getMessage());
    }
}
