package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.front.controller.rest.AuthorRequest;

import java.util.UUID;


public class AuthorRequestTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(AuthorRequest.class)
                .addTemplate("valid AuthorRequest", new Rule() {
                    {
                        add("name", UUID.randomUUID().toString());
                    }
                });
    }
}
