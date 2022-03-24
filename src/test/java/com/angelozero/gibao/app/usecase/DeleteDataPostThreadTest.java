package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.PropertiesConfig;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPostThread;
import com.angelozero.gibao.app.usecase.datapost.FindDataPost;
import com.angelozero.gibao.app.usecase.datapost.ValidateDataPost;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MockitoSettings
class DeleteDataPostThreadTest {

    @Mock
    private PropertiesConfig propertiesConfig;

    @Mock
    private DeleteDataPost deleteDataPost;

    @Mock
    private ValidateDataPost validateDataPost;

    @Mock
    private FindDataPost findDataPost;

    @InjectMocks
    private DeleteDataPostThread deleteDataPostThread;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    @DisplayName("Should delete a post data in a thread with success")
    void shouldDeleteAPostDataInAThreadWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        doNothing().when(validateDataPost).execute(dataPostMock);
        lenient().doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(dataPostMock);

        verify(validateDataPost, times(1)).execute(dataPostMock);
    }

    @Test
    @DisplayName("Should delete a post data in a thread true secret user with success")
    void shouldDeleteAPostDataInAThreadTrueSecretUserWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        dataPostMock.setSecretUser(Boolean.TRUE);

        lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        doNothing().when(validateDataPost).execute(dataPostMock);
        lenient().doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(dataPostMock);

        verify(validateDataPost, times(1)).execute(dataPostMock);
    }

    @Test
    @DisplayName("Should delete a post data in a thread without secret user with success")
    void shouldDeleteAPostDataInAThreadWithoutSecretUserWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");

        lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        doNothing().when(validateDataPost).execute(dataPostMock);
        lenient().doNothing().when(deleteDataPost).execute(dataPostMock.getId());
        lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(dataPostMock);

        verify(validateDataPost, times(1)).execute(dataPostMock);
    }

    @Test
    @DisplayName("Should delete a post data in a thread with null post data with success")
    void shouldDeleteAPostDataInAThreadWithNullPostDataWithSuccess() {

        lenient().when(propertiesConfig.getMinutes()).thenReturn(1L);
        doNothing().when(validateDataPost).execute(null);
        lenient().when(findDataPost.execute(anyLong())).thenReturn(null);

        deleteDataPostThread.execute(null);

        verify(validateDataPost, times(1)).execute(null);
    }

    //TODO how to test a new Thread?
}
