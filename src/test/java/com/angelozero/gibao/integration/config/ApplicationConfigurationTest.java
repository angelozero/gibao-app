package com.angelozero.gibao.integration.config;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.testcontainers.containers.PostgreSQLContainer;
import redis.embedded.RedisServer;


@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ApplicationConfigurationTest.Initializer.class)
public class ApplicationConfigurationTest {

    /**
     * REDIS
     */
    private final RedisServer redis = new RedisServer();

    @BeforeEach
    public void beforeEach() {
        if (!redis.isActive()) {
            redis.start();
        }
    }

    @AfterEach
    public void afterEach() {
        if (redis.isActive()) {
            redis.stop();
        }
    }

    /**
     * * Testcontainer - PostgreSQL
     **/
    @ClassRule
    public static PostgreSQLContainer container = new PostgreSQLContainer()
            .withDatabaseName("gibao-app-test-db")
            .withPassword("password")
            .withUsername("user");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.datasource.username=" + container.getUsername()
            );

            values.applyTo(configurableApplicationContext);
        }
    }
}
