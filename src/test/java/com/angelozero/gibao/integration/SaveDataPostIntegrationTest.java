package com.angelozero.gibao.integration;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import com.angelozero.gibao.integration.config.ApplicationConfigurationTest;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class SaveDataPostIntegrationTest extends ApplicationConfigurationTest {

    @Autowired
    DataPostJPARepository repository;

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void firstTest() {

        DataPostModel dataPost = Fixture.from(DataPostModel.class).gimme("valid DataPostModel to save");
        repository.save(dataPost);

        List<DataPostModel> list = repository.findAll();
        assertEquals(1, list.size());
    }


}
