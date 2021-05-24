package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.domain.DataPost;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;

public class DeleteDataPostThreadTest {

    private final PropertiesConfig propertiesConfig = Mockito.mock(PropertiesConfig.class);
    private final DeleteDataPost deleteDataPost = Mockito.mock(DeleteDataPost.class);
    private final ValidateDataPost validateDataPost = Mockito.mock(ValidateDataPost.class);
    private final FindDataPost findDataPost = Mockito.mock(FindDataPost.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    public void shouldDeleteAPostDataInAThreadWithSuccess() throws InterruptedException {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(dataPostMock);
        Mockito.doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        Mockito.when(findDataPost.execute(anyLong())).thenReturn(null);

        DeleteDataPostThread deleteDataPostThread = new DeleteDataPostThread(propertiesConfig, deleteDataPost, validateDataPost, findDataPost);
        deleteDataPostThread.execute(dataPostMock);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(dataPostMock);

    }

    @Test
    public void shouldDeleteAPostDataInAThreadTrueSecretUserWithSuccess() throws InterruptedException {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        dataPostMock.setSecretUser(Boolean.TRUE);

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(dataPostMock);
        Mockito.doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        Mockito.when(findDataPost.execute(anyLong())).thenReturn(null);

        DeleteDataPostThread deleteDataPostThread = new DeleteDataPostThread(propertiesConfig, deleteDataPost, validateDataPost, findDataPost);
        deleteDataPostThread.execute(dataPostMock);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(dataPostMock);

    }

    @Test
    public void shouldDeleteAPostDataInAThreadWhithoutSecretUserWithSuccess() throws InterruptedException {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(dataPostMock);
        Mockito.doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        Mockito.when(findDataPost.execute(anyLong())).thenReturn(null);

        DeleteDataPostThread deleteDataPostThread = new DeleteDataPostThread(propertiesConfig, deleteDataPost, validateDataPost, findDataPost);
        deleteDataPostThread.execute(dataPostMock);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(dataPostMock);

    }

    @Test
    public void shouldDeleteAPostDataInAThreadWhithNullPostDataWithSuccess() throws InterruptedException {

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validateDataPost).execute(null);
        Mockito.when(findDataPost.execute(anyLong())).thenReturn(null);

        DeleteDataPostThread deleteDataPostThread = new DeleteDataPostThread(propertiesConfig, deleteDataPost, validateDataPost, findDataPost);
        deleteDataPostThread.execute(null);

        Mockito.verify(validateDataPost, Mockito.times(1)).execute(null);
    }

    //TODO how to test a new Thread?
}
