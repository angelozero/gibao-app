package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.util.MessageInfo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class GetPokemonByNumberTest {

    private final PokemonApi pokemonApi = Mockito.mock(PokemonApi.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldGetAPokemonByNameWithSuccess() {

        Pokemon pokemonMock = Fixture.from(Pokemon.class).gimme("valid Pokemon");

        Mockito.when(pokemonApi.getImageByNumber(Mockito.anyInt())).thenReturn(pokemonMock);

        GetPokemonByNumber getPokemonByNumber = new GetPokemonByNumber(pokemonApi);

        String pokemon = getPokemonByNumber.execute();

        assertNotNull(getPokemonByNumber);
        assertEquals(pokemonMock.getSprites().getOther().getOfficialArtWork().getFrontDefault(), pokemon);
    }

    @Test
    public void shoulThrowAnExceptionWhenGetPokemonByName() {
        Mockito.when(pokemonApi.getImageByNumber(Mockito.anyInt())).thenThrow(new RuntimeException("Error to get pokemon by number test"));

        GetPokemonByNumber getPokemonByNumber = new GetPokemonByNumber(pokemonApi);

        PokemonApiException exception = assertThrows(PokemonApiException.class, getPokemonByNumber::execute);

        assertNotNull(exception);
        assertEquals(MessageInfo.GET_POKEMON_BY_NUMBER_ERROR_INFO.replace("%s", "") + "Error to get pokemon by number test", exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
