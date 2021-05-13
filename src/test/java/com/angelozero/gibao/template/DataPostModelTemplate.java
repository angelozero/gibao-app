package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;


public class DataPostModelTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(DataPostModel.class)
                .addTemplate("valid DataPostModel", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("author", UUID.randomUUID().toString());
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                        add("date", LocalDate.now());
                        add("secretUser", Math.random() < 0.5);
                    }
                })
                .addTemplate("valid DataPostModel without SecretUser", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("author", UUID.randomUUID().toString());
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                        add("date", LocalDate.now());
                    }
                });
    }
}
