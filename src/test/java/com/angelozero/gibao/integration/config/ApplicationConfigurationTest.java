package com.angelozero.gibao.integration.config;


import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.JsonReaderIntegrationTestException;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileReader;
import java.io.IOException;


@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ApplicationConfigurationTest.Initializer.class)
public class ApplicationConfigurationTest {

    protected static final String GET_DATA_POST_JSON = "/gibao-app/data/json";
    protected static final String GET_DATA_POST = "/gibao-app/data";
    protected static final String POKEMON_API_ID = "/pokemon-api/[0-9]+";
    protected static final String POKEMON_API_MEWTWO_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/150.png";

    @Autowired
    protected DataPostJPARepository repository;


    /**
     * Testcontainer - REDIS
     */
    @ClassRule
    public static GenericContainer redisContainer = new GenericContainer("redis:latest")
            .withExposedPorts(6379);


    /**
     * Testcontainer - PostgreSQL
     */
    @ClassRule
    public static PostgreSQLContainer container = new PostgreSQLContainer()
            .withDatabaseName("gibao-app-test-db")
            .withPassword("password")
            .withUsername("user");


    /**
     * Configuration initializer - .properties file
     */
    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.redis.port=" + redisContainer.getFirstMappedPort()
            );

            values.applyTo(configurableApplicationContext);

            //Setting Redis config values in runtime
            System.setProperty("redis.port.value", redisContainer.getFirstMappedPort().toString());
        }

    }

    /**
     * Get json files
     */
    protected String getJsonValue(String file) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader("src/test/resources/json-responses/" + file));

            return data.toJSONString();
        } catch (IOException | ParseException e) {
            throw new JsonReaderIntegrationTestException(Error.builder()
                    .message("Error to read json file " + file)
                    .identifier(file)
                    .build());
        }
    }

    protected void clearRepository() {
        this.repository.deleteAll();
    }

}
