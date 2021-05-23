package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class SaveDataPostTest {

    private final DataPostGateway dataPostGateway = Mockito.mock(DataPostGateway.class);
    private final ValidateDataPost validateDataPost = Mockito.mock(ValidateDataPost.class);
    private final DeleteDataPostThread deleteDataPostThread = Mockito.mock(DeleteDataPostThread.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldSaveADataPostWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.doNothing().when(validateDataPost).execute(any(DataPost.class));
        Mockito.when(dataPostGateway.save(any(DataPost.class))).thenReturn(dataPostMock);
        Mockito.doNothing().when(deleteDataPostThread).execute(any(DataPost.class));

        SaveDataPost saveDataPost = new SaveDataPost(dataPostGateway, validateDataPost, deleteDataPostThread);
        saveDataPost.execute(dataPostMock);

        Mockito.verify(dataPostGateway, Mockito.times(1)).save(any(DataPost.class));
        Mockito.verify(deleteDataPostThread, Mockito.times(1)).execute(any(DataPost.class));
    }

    @Test
    public void shouldThrowAnExceptionWhenSaveADataPostErrorOnGateway() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.doNothing().when(validateDataPost).execute(any(DataPost.class));
        Mockito.when(dataPostGateway.save(any(DataPost.class))).thenThrow(new RuntimeException("Error to save data post test"));
        Mockito.doNothing().when(deleteDataPostThread).execute(any(DataPost.class));

        SaveDataPost saveDataPost = new SaveDataPost(dataPostGateway, validateDataPost, deleteDataPostThread);
        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> saveDataPost.execute(dataPostMock));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.SAVE_DATA_POST_ERROR, "Error to save data post test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldThrowAnExceptionWhenSaveADataPostErrorOnThread() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.doNothing().when(validateDataPost).execute(any(DataPost.class));
        Mockito.when(dataPostGateway.save(any(DataPost.class))).thenReturn(dataPostMock);
        Mockito.doThrow(new RuntimeException("Error to save data post test")).when(deleteDataPostThread).execute(any(DataPost.class));

        SaveDataPost saveDataPost = new SaveDataPost(dataPostGateway, validateDataPost, deleteDataPostThread);
        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> saveDataPost.execute(dataPostMock));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.SAVE_DATA_POST_ERROR, "Error to save data post test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
