package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.domain.Pokemon;


public class SpritesTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Pokemon.Sprites.class)
                .addTemplate("valid Sprites", new Rule() {
                    {
                        add("other", one(Pokemon.Other.class, "valid Other"));
                    }
                });
    }
}
