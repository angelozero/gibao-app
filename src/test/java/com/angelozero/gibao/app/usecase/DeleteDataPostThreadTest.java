package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.domain.DataPost;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class DeleteDataPostThreadTest {

    private final PropertiesConfig propertiesConfig = Mockito.mock(PropertiesConfig.class);
    private final DeletePostData deletePostData = Mockito.mock(DeletePostData.class);
    private final ValidatePostData validatePostData = Mockito.mock(ValidatePostData.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    public void shouldDeleteAPostDataInAThreadWithSuccess() throws InterruptedException {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validatePostData).execute(dataPostMock);
        Mockito.doNothing().when(deletePostData).execute(dataPostMock.getId());

        DeletePostDataThread deletePostDataThread = new DeletePostDataThread(propertiesConfig, deletePostData, validatePostData);
        deletePostDataThread.execute(dataPostMock);

        Mockito.verify(validatePostData, Mockito.times(1)).execute(dataPostMock);

    }

    @Test
    public void shouldDeleteAPostDataInAThreadTrueSecretUserWithSuccess() throws InterruptedException {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        dataPostMock.setSecretUser(Boolean.TRUE);

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validatePostData).execute(dataPostMock);
        Mockito.doNothing().when(deletePostData).execute(dataPostMock.getId());

        DeletePostDataThread deletePostDataThread = new DeletePostDataThread(propertiesConfig, deletePostData, validatePostData);
        deletePostDataThread.execute(dataPostMock);

        Mockito.verify(validatePostData, Mockito.times(1)).execute(dataPostMock);

    }

    @Test
    public void shouldDeleteAPostDataInAThreadWhithoutSecretUserWithSuccess() throws InterruptedException {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validatePostData).execute(dataPostMock);
        Mockito.doNothing().when(deletePostData).execute(dataPostMock.getId());

        DeletePostDataThread deletePostDataThread = new DeletePostDataThread(propertiesConfig, deletePostData, validatePostData);
        deletePostDataThread.execute(dataPostMock);

        Mockito.verify(validatePostData, Mockito.times(1)).execute(dataPostMock);

    }

    @Test
    public void shouldDeleteAPostDataInAThreadWhithNullPostDataWithSuccess() throws InterruptedException {

        Mockito.when(propertiesConfig.getOneMinute()).thenReturn(1L);
        Mockito.doNothing().when(validatePostData).execute(null);

        DeletePostDataThread deletePostDataThread = new DeletePostDataThread(propertiesConfig, deletePostData, validatePostData);
        deletePostDataThread.execute(null);

        Mockito.verify(validatePostData, Mockito.times(1)).execute(null);

    }

    //TODO how to test a new Thread?

}
