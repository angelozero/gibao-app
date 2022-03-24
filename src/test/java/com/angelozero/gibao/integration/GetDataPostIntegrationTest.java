package com.angelozero.gibao.integration;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.front.controller.DataPostController;
import com.angelozero.gibao.integration.config.ApplicationConfigurationTest;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

@RunWith(SpringRunner.class)
public class GetDataPostIntegrationTest extends ApplicationConfigurationTest {

    @Autowired
    private DataPostController controller;

    private MockMvc mockMvc;

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9898);

    @Before
    public void setUp() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldGetDataPostWithSuccessFromDB() throws Exception {

        // Given
        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel integration test");
        repository.save(dataPost);

        List<DataPostModel> list = repository.findAll();
        assertEquals(1, list.size());

        this.wireMockRule.stubFor(get(urlMatching(POKEMON_API_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        Pokemon.class.getClassLoader().getResourceAsStream("pokemonapi/mewtwo.json"),
                                        defaultCharset()))));

        // Then
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .get(GET_DATA_POST)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);
        assertNotNull(result.getModelAndView());
        assertNotNull(result.getModelAndView().getModel());
        assertEquals(result.getModelAndView().getModel().get("pokemon"), POKEMON_API_MEWTWO_URL);
        assertEquals(result.getModelAndView().getModel().get("description"), dataPost.getDescription());

        clearRepository();
    }

    @Test
    public void shouldGetDataPostWithSuccessFromDBJsonResponse() throws Exception {

        // Given
        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel integration test");
        repository.save(dataPost);

        List<DataPostModel> list = repository.findAll();
        assertEquals(1, list.size());

        this.wireMockRule.stubFor(get(urlMatching(POKEMON_API_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        Pokemon.class.getClassLoader().getResourceAsStream("pokemonapi/mewtwo.json"),
                                        defaultCharset()))));

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(GET_DATA_POST_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonValue("saved-post.json")));

        clearRepository();

    }

    @Test
    public void shouldGetDataPostWithSuccessFromRedis() throws Exception {

        // Given
        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel integration test");
        repository.save(dataPost);

        List<DataPostModel> list = repository.findAll();
        assertEquals(1, list.size());

        this.wireMockRule.stubFor(get(urlMatching(POKEMON_API_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        Pokemon.class.getClassLoader().getResourceAsStream("pokemonapi/mewtwo.json"),
                                        defaultCharset()))));

        MvcResult resultFirstCall = this.mockMvc.perform(MockMvcRequestBuilders
                .get(GET_DATA_POST)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(resultFirstCall);
        assertNotNull(resultFirstCall.getModelAndView());
        assertNotNull(resultFirstCall.getModelAndView().getModel());
        assertEquals(resultFirstCall.getModelAndView().getModel().get("pokemon"), POKEMON_API_MEWTWO_URL);
        assertEquals(resultFirstCall.getModelAndView().getModel().get("description"), dataPost.getDescription());

        // When
        repository.deleteAll();
        List<DataPostModel> dataRepository = repository.findAll();
        assertTrue(dataRepository.isEmpty());

        // Then
        MvcResult resultSecondCall = this.mockMvc.perform(MockMvcRequestBuilders
                .get(GET_DATA_POST)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(resultSecondCall);
        assertNotNull(resultSecondCall.getModelAndView());
        assertNotNull(resultSecondCall.getModelAndView().getModel());
        assertEquals(resultSecondCall.getModelAndView().getModel().get("pokemon"), POKEMON_API_MEWTWO_URL);
        assertEquals(resultSecondCall.getModelAndView().getModel().get("description"), dataPost.getDescription());

        clearRepository();
    }

    @Test
    public void shouldGetDataPostWithSuccessFromRedisJsonResponse() throws Exception {

        // Given
        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel integration test");
        repository.save(dataPost);

        List<DataPostModel> list = repository.findAll();
        assertEquals(1, list.size());

        this.wireMockRule.stubFor(get(urlMatching(POKEMON_API_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        Pokemon.class.getClassLoader().getResourceAsStream("pokemonapi/mewtwo.json"),
                                        defaultCharset()))));

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(GET_DATA_POST_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonValue("saved-post.json")));

        // When
        repository.deleteAll();
        List<DataPostModel> dataRepository = repository.findAll();
        assertTrue(dataRepository.isEmpty());

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get(GET_DATA_POST_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonValue("saved-post.json")));

        clearRepository();
    }

    @Test
    public void shouldGetDataPostWithClientError() {

        // Given
        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel integration test");
        repository.save(dataPost);

        List<DataPostModel> list = repository.findAll();
        assertEquals(1, list.size());

        this.wireMockRule.stubFor(get(urlMatching(POKEMON_API_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));

        // Then
        // Why I can't test the exception returned ? https://github.com/spring-projects/spring-boot/issues/7321
        Assertions
                .assertThatThrownBy(() ->
                        this.mockMvc.perform(MockMvcRequestBuilders
                                .get(GET_DATA_POST)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .hasCauseInstanceOf(PokemonApiException.class);

        clearRepository();
    }
}
