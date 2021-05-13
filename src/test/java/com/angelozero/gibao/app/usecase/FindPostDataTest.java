package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import org.junit.BeforeClass;
import org.mockito.Mockito;

public class FindPostDataTest {

    private final DataPostGateway dataPostGateway = Mockito.mock(DataPostGateway.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


}
