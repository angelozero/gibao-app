package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.domain.Pokemon;

import java.util.UUID;


public class OfficialArtWorkTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Pokemon.OfficialArtWork.class)
                .addTemplate("valid OfficialArtWork", new Rule() {
                    {
                        add("frontDefault", UUID.randomUUID().toString());
                    }
                });
    }
}
