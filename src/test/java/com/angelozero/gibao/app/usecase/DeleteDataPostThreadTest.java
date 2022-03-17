package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPostThread;
import com.angelozero.gibao.app.usecase.datapost.FindDataPost;
import com.angelozero.gibao.app.usecase.datapost.ValidateDataPost;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class DeleteDataPostThreadTest {

    @Mock
    private PropertiesConfig propertiesConfig;

    @Mock
    private DeleteDataPost deleteDataPost;

    @Mock
    private ValidateDataPost validateDataPost;

    @Mock
    private FindDataPost findDataPost;

    @InjectMocks
    DeleteDataPostThread deleteDataPostThread;

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    public void shouldDeleteAPostDataInAThreadWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(dataPostMock);
        Mockito.lenient().doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        Mockito.lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(dataPostMock);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(dataPostMock);
    }

    @Test
    public void shouldDeleteAPostDataInAThreadTrueSecretUserWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        dataPostMock.setSecretUser(Boolean.TRUE);

        Mockito.lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(dataPostMock);
        Mockito.lenient().doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        Mockito.lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(dataPostMock);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(dataPostMock);
    }

    @Test
    public void shouldDeleteAPostDataInAThreadWhithoutSecretUserWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");

        Mockito.lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(dataPostMock);
        Mockito.lenient().doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        Mockito.lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(dataPostMock);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(dataPostMock);
    }

    @Test
    public void shouldDeleteAPostDataInAThreadWhithNullPostDataWithSuccess() {

        Mockito.lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(null);
        Mockito.lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(null);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(null);
    }

    //TODO how to test a new Thread?
}
