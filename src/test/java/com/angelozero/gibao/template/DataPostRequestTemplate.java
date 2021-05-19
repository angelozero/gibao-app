package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.front.controller.rest.AuthorRequest;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;

import java.util.Random;
import java.util.UUID;


public class DataPostRequestTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(DataPostRequest.class)
                .addTemplate("valid DataPostRequest", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("author", one(AuthorRequest.class, "valid AuthorRequest"));
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                    }
                })
                .addTemplate("valid DataPostRequest without Author", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                    }
                });
    }
}

