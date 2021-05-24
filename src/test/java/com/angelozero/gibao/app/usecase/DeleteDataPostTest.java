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
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteDataPostTest {

    private final DataPostGateway dataPostGateway = Mockito.mock(DataPostGateway.class);
    private final FindDataPost findDataPost = Mockito.mock(FindDataPost.class);
    private final RedisService redisService = Mockito.mock(RedisService.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldExecuteADeleteAPostDataWithSuccess() {

        Long id = new Random().nextLong();
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        Mockito.when(findDataPost.execute(id)).thenReturn(dataPostMock);
        Mockito.doNothing().when(dataPostGateway).deleteById(id);

        Mockito.when(redisService.findAll(Mockito.any())).thenReturn(Collections.emptyList());


        DeleteDataPost deleteDataPost = new DeleteDataPost(dataPostGateway, findDataPost, redisService);
        deleteDataPost.execute(id);

        Mockito.verify(dataPostGateway, Mockito.times(1)).deleteById(id);

    }

    @Test
    public void shouldExecuteADeleteAPostDataNotCallingDeleteByIdWithSuccess() {

        Long id = new Random().nextLong();
        Mockito.when(findDataPost.execute(id)).thenReturn(null);
        Mockito.doNothing().when(dataPostGateway).deleteById(id);
        Mockito.when(redisService.findAll(Mockito.any())).thenReturn(Collections.emptyList());

        DeleteDataPost deleteDataPost = new DeleteDataPost(dataPostGateway, findDataPost, redisService);
        deleteDataPost.execute(id);

        Mockito.verify(dataPostGateway, Mockito.times(0)).deleteById(id);

    }

    @Test
    public void shouldThrowAnErrorWhenExecuteADeleteAPostData() {

        Long id = new Random().nextLong();
        Mockito.when(findDataPost.execute(id)).thenThrow(new RuntimeException("Fail to delete a post data test"));
        Mockito.doNothing().when(dataPostGateway).deleteById(id);
        Mockito.when(redisService.findAll(Mockito.any())).thenReturn(Collections.emptyList());

        DeleteDataPost deleteDataPost = new DeleteDataPost(dataPostGateway, findDataPost, redisService);
        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> deleteDataPost.execute(id));

        Mockito.verify(dataPostGateway, Mockito.times(0)).deleteById(id);
        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, "Fail to delete a post data test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());

    }

    @Test
    public void shouldThrowAnErrorWhenExecuteADeleteAPostDataWhitValidFindPostDataReturned() {

        Long id = new Random().nextLong();
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        Mockito.when(findDataPost.execute(id)).thenReturn(dataPostMock);
        Mockito.when(redisService.findAll(Mockito.any())).thenReturn(Collections.emptyList());
        Mockito.doThrow(new RuntimeException("Fail to delete a post data gateway test")).when(dataPostGateway).deleteById(id);

        DeleteDataPost deleteDataPost = new DeleteDataPost(dataPostGateway, findDataPost, redisService);
        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> deleteDataPost.execute(id));

        Mockito.verify(dataPostGateway, Mockito.times(1)).deleteById(id);
        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.DELETE_DATA_POST_ERROR, "Fail to delete a post data gateway test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());

    }

}
