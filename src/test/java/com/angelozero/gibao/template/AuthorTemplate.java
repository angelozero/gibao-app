package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.domain.Author;

import java.util.UUID;


public class AuthorTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Author.class)
                .addTemplate("valid Author", new Rule() {
                    {
                        add("name", UUID.randomUUID().toString());
                    }
                });
    }
}
