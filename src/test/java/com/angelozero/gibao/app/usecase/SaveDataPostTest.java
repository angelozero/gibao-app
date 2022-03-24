package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPostThread;
import com.angelozero.gibao.app.usecase.datapost.SaveDataPost;
import com.angelozero.gibao.app.usecase.datapost.ValidateDataPost;
import com.angelozero.gibao.app.usecase.enums.RedisInfo;
import com.angelozero.gibao.app.usecase.redis.RedisService;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
class SaveDataPostTest {

    @Mock
    private DataPostGateway dataPostGateway;

    @Mock
    private ValidateDataPost validateDataPost;

    @Mock
    private DeleteDataPostThread deleteDataPostThread;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private SaveDataPost saveDataPost;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    @DisplayName("Should save a data post with success")
    void shouldSaveADataPostWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        doNothing().when(validateDataPost).execute(any(DataPost.class));
        doNothing().when(redisService).delete(RedisInfo.HASH_KEY_DATA_POST);
        when(dataPostGateway.save(any(DataPost.class))).thenReturn(dataPostMock);
        doNothing().when(deleteDataPostThread).execute(any(DataPost.class));

        saveDataPost.execute(dataPostMock);

        verify(dataPostGateway, times(1)).save(any(DataPost.class));
        verify(deleteDataPostThread, times(1)).execute(any(DataPost.class));
    }

    @Test
    @DisplayName("Should thrown an exception in gateway when save a data post")
    void shouldThrowAnExceptionWhenSaveADataPostErrorOnGateway() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        doNothing().when(validateDataPost).execute(any(DataPost.class));
        when(dataPostGateway.save(any(DataPost.class))).thenThrow(new RuntimeException("Error to save data post test"));
        lenient().doNothing().when(deleteDataPostThread).execute(any(DataPost.class));

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> saveDataPost.execute(dataPostMock));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.SAVE_DATA_POST_ERROR, "Error to save data post test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    @DisplayName("Should thrown an exception in thread when save a data post")
    void shouldThrowAnExceptionWhenSaveADataPostErrorOnThread() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        doNothing().when(validateDataPost).execute(any(DataPost.class));
        when(dataPostGateway.save(any(DataPost.class))).thenReturn(dataPostMock);
        doThrow(new RuntimeException("Error to save data post test")).when(deleteDataPostThread).execute(any(DataPost.class));

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> saveDataPost.execute(dataPostMock));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.SAVE_DATA_POST_ERROR, "Error to save data post test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }
}
