package com.angelozero.gibao.integration;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.domain.Pokemon;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import com.angelozero.gibao.front.controller.DataPostController;
import com.angelozero.gibao.integration.config.ApplicationConfigurationTest;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

//@RunWith(SpringRunner.class)
public class SaveDataPostIntegrationTest extends ApplicationConfigurationTest {

    private static final String URL_DATA_POST = "/gibao-app/data/json";
    public static final String POKEMON_API_ID = "/pokemon-api/[0-9]+";

    @Autowired
    DataPostJPARepository repository;

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

//    @Test
    public void shouldGetADataPostInPostgresFromTheFirstTimeAndShouldGetTheSameInformationInRedisFromSecondTime() throws Exception {

//        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel to save");
//        repository.save(dataPost);
//
//        List<DataPostModel> list = repository.findAll();
//        assertEquals(1, list.size());
//
//        this.wireMockRule.stubFor(get(urlMatching(POKEMON_API_ID))
//                .willReturn(aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(
//                                copyToString(
//                                        Pokemon.class.getClassLoader().getResourceAsStream("pokemonapi/mewtwo.json"),
//                                        defaultCharset()))));
//
//        this.mockMvc.perform(MockMvcRequestBuilders
//                .get(URL_DATA_POST)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(HttpStatus.OK.value()))
//                .andExpect(content().json("json_resposta"));

    }
}
