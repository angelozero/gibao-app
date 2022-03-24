package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PokemonPropertiesConfig;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomNumber;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@MockitoSettings
class GetRandomPokemonByRandomNumberTest {

    static final int FIRST_SEASON_COUNT = 150;

    @Mock
    private PokemonPropertiesConfig pokemonPropertiesConfig;

    @Mock
    private PokemonApi pokemonApi;

    @InjectMocks
    private GetPokemonByRandomNumber getPokemonByRandomNumber;


    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    @DisplayName("Should get a random pokemon by number with success")
    void shouldGetARandomPokemonByNumberWithSuccess() {

        Pokemon pokemonMock = Fixture.from(Pokemon.class).gimme("valid Pokemon");

        when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(FIRST_SEASON_COUNT);
        when(pokemonApi.getImageByNumber(anyInt())).thenReturn(pokemonMock);

        String pokemon = getPokemonByRandomNumber.execute();

        assertNotNull(getPokemonByRandomNumber);
        assertEquals(pokemonMock.getSprites().getOther().getOfficialArtWork().getFrontDefault(), pokemon);
    }

    @Test
    @DisplayName("Should thrown an exception when get a random pokemon by number")
    void shouldThrowAnExceptionWhenGetARandomPokemonByNumber() {

        when(pokemonPropertiesConfig.getSeasonCount()).thenReturn(FIRST_SEASON_COUNT);
        when(pokemonApi.getImageByNumber(anyInt())).thenThrow(new RuntimeException("Error to get pokemon by number test"));

        PokemonApiException exception = assertThrows(PokemonApiException.class, getPokemonByRandomNumber::execute);

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NUMBER_ERROR, "Error to get pokemon by number test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
