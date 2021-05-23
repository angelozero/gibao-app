package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.domain.Author;
import com.angelozero.gibao.app.domain.DataPost;

import java.time.LocalDate;
import java.util.*;


public class DataPostTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(DataPost.class)
                .addTemplate("valid DataPost", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("author", one(Author.class, "valid Author"));
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                        add("infoDate", LocalDate.now().toString());
                        add("secretUser", Math.random() < 0.5);
                    }
                })
                .addTemplate("valid DataPost without Author", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                        add("infoDate", LocalDate.now().toString());
                        add("secretUser", Math.random() < 0.5);
                    }
                })
                .addTemplate("valid DataPost without SecretUser", new Rule() {
                    {
                        add("id", new Random().nextLong());
                        add("author", one(Author.class, "valid Author"));
                        add("title", UUID.randomUUID().toString());
                        add("description", UUID.randomUUID().toString());
                        add("infoDate", LocalDate.now().toString());
                    }
                });
    }
}
