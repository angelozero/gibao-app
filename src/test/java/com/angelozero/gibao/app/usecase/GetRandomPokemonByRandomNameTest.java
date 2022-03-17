package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomName;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GetRandomPokemonByRandomNameTest {

    private static final String PIKACHU = "pikachu";
    private final PropertiesConfig propertiesConfig = Mockito.mock(PropertiesConfig.class);
    private final PokemonApi pokemonApi = Mockito.mock(PokemonApi.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldGetAPokemonByNameWithSuccess() {

        Pokemon pokemonMock = Fixture.from(Pokemon.class).gimme("valid Pokemon");

        Mockito.when(propertiesConfig.getPokemonList()).thenReturn(Collections.singletonList(PIKACHU));
        Mockito.when(pokemonApi.getImageByName(PIKACHU)).thenReturn(pokemonMock);

        GetPokemonByRandomName getPokemonByRandomName = new GetPokemonByRandomName(propertiesConfig, pokemonApi);

        String pokemon = getPokemonByRandomName.execute();

        assertNotNull(getPokemonByRandomName);
        assertEquals(pokemonMock.getSprites().getOther().getOfficialArtWork().getFrontDefault(), pokemon);
    }

    @Test
    public void shoulThrowAnExceptionWhenGetPokemonByName() {
        Mockito.when(propertiesConfig.getPokemonList()).thenReturn(Collections.singletonList(PIKACHU));
        Mockito.when(pokemonApi.getImageByName(Mockito.anyString())).thenThrow(new RuntimeException("Error to get pokemon by name test"));

        GetPokemonByRandomName getPokemonByRandomName = new GetPokemonByRandomName(propertiesConfig, pokemonApi);

        PokemonApiException exception = assertThrows(PokemonApiException.class, getPokemonByRandomName::execute);

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NAME_ERROR, "Error to get pokemon by name test"), exception.getError().getMessage());
    }
}
