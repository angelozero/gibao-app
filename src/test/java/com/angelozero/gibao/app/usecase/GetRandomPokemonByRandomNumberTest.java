package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomNumber;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class GetRandomPokemonByRandomNumberTest {

    private final PokemonApi pokemonApi = Mockito.mock(PokemonApi.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldGetAPokemonByNameWithSuccess() {

        Pokemon pokemonMock = Fixture.from(Pokemon.class).gimme("valid Pokemon");

        Mockito.when(pokemonApi.getImageByNumber(Mockito.anyInt())).thenReturn(pokemonMock);

        GetPokemonByRandomNumber getPokemonByRandomNumber = new GetPokemonByRandomNumber(pokemonApi);

        String pokemon = getPokemonByRandomNumber.execute();

        assertNotNull(getPokemonByRandomNumber);
        assertEquals(pokemonMock.getSprites().getOther().getOfficialArtWork().getFrontDefault(), pokemon);
    }

    @Test
    public void shoulThrowAnExceptionWhenGetPokemonByName() {
        Mockito.when(pokemonApi.getImageByNumber(Mockito.anyInt())).thenThrow(new RuntimeException("Error to get pokemon by number test"));

        GetPokemonByRandomNumber getPokemonByRandomNumber = new GetPokemonByRandomNumber(pokemonApi);

        PokemonApiException exception = assertThrows(PokemonApiException.class, getPokemonByRandomNumber::execute);

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NUMBER_ERROR, "Error to get pokemon by number test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
