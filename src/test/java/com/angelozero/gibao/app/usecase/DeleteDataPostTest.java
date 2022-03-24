package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPost;
import com.angelozero.gibao.app.usecase.datapost.FindDataPost;
import com.angelozero.gibao.app.usecase.redis.RedisService;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
class DeleteDataPostTest {

    @Mock
    private DataPostGateway dataPostGateway;

    @Mock
    private FindDataPost findDataPost;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private DeleteDataPost deleteDataPost;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    @DisplayName("Should delete a post data with success")
    void shouldExecuteADeleteAPostDataWithSuccess() {

        Long id = new Random().nextLong();
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        when(findDataPost.execute(id)).thenReturn(dataPostMock);
        doNothing().when(dataPostGateway).deleteById(id);

        when(redisService.findAll(any())).thenReturn(Collections.emptyList());

        deleteDataPost.execute(id);

        verify(dataPostGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should delete a post data not calling delete by id with success")
    void shouldExecuteADeleteAPostDataNotCallingDeleteByIdWithSuccess() {

        Long id = new Random().nextLong();
        when(findDataPost.execute(id)).thenReturn(null);
        lenient().doNothing().when(dataPostGateway).deleteById(id);
        when(redisService.findAll(any())).thenReturn(Collections.emptyList());

        deleteDataPost.execute(id);

        verify(dataPostGateway, times(0)).deleteById(id);
    }

    @Test
    @DisplayName("Should thrown an error when delete a post data")
    void shouldThrowAnErrorWhenExecuteADeleteAPostData() {

        Long id = new Random().nextLong();
        when(findDataPost.execute(id)).thenThrow(new RuntimeException("Fail to delete a post data test"));
        lenient().doNothing().when(dataPostGateway).deleteById(id);
        lenient().when(redisService.findAll(any())).thenReturn(Collections.emptyList());

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> deleteDataPost.execute(id));

        verify(dataPostGateway, times(0)).deleteById(id);
        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, "Fail to delete a post data test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }

    @Test
    @DisplayName("Should thrown an error when delete a found and valid post data")
    void shouldThrowAnErrorWhenExecuteADeleteAPostDataWhitValidFindPostDataReturned() {

        Long id = new Random().nextLong();
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        when(findDataPost.execute(id)).thenReturn(dataPostMock);
        lenient().when(redisService.findAll(any())).thenReturn(Collections.emptyList());
        doThrow(new RuntimeException("Fail to delete a post data gateway test")).when(dataPostGateway).deleteById(id);

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> deleteDataPost.execute(id));

        verify(dataPostGateway, times(1)).deleteById(id);
        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, "Fail to delete a post data gateway test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());

    }
}
