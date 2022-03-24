package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PokemonPropertiesConfig;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomName;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@MockitoSettings
class GetRandomPokemonByRandomNameTest {

    private static final String PIKACHU = "pikachu";

    @Mock
    private PokemonPropertiesConfig pokemonPropertiesConfig;

    @Mock
    private PokemonApi pokemonApi;

    @InjectMocks
    private GetPokemonByRandomName getPokemonByRandomName;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    @DisplayName("Should get a random pokemon by name with success")
    void shouldGetARandomPokemonByRandomNameWithSuccess() {

        Pokemon pokemonMock = Fixture.from(Pokemon.class).gimme("valid Pokemon");

        when(pokemonPropertiesConfig.getSeasonNamesList()).thenReturn(Collections.singletonList(PIKACHU));
        when(pokemonApi.getImageByName(PIKACHU)).thenReturn(pokemonMock);

        String pokemon = getPokemonByRandomName.execute();

        assertNotNull(getPokemonByRandomName);
        assertEquals(pokemonMock.getSprites().getOther().getOfficialArtWork().getFrontDefault(), pokemon);
    }

    @Test
    @DisplayName("Should thrown an exception when get a random pokemon by name")
    void shoulThrowAnExceptionWhenGetRandomPokemonByName() {

        when(pokemonPropertiesConfig.getSeasonNamesList()).thenReturn(Collections.singletonList(PIKACHU));
        when(pokemonApi.getImageByName(anyString())).thenThrow(new RuntimeException("Error to get pokemon by name test"));

        PokemonApiException exception = assertThrows(PokemonApiException.class, getPokemonByRandomName::execute);

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.GET_POKEMON_BY_NAME_ERROR, "Error to get pokemon by name test"), exception.getError().getMessage());
    }
}
