package com.angelozero.gibao.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.angelozero.gibao.app.domain.Pokemon;


public class OtherTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Pokemon.Other.class)
                .addTemplate("valid Other", new Rule() {
                    {
                        add("officialArtWork", one(Pokemon.OfficialArtWork.class, "valid OfficialArtWork"));
                    }
                });
    }
}
