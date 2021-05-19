package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.domain.Pokemon;


public class PokemonTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Pokemon.class)
                .addTemplate("valid Pokemon", new Rule() {
                    {
                        add("sprites", one(Pokemon.Sprites.class, "valid Sprites"));
                    }
                });
    }
}
