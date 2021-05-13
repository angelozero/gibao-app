package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.util.MessageInfo;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteDataPostTest {

    private final DataPostGateway dataPostGateway = Mockito.mock(DataPostGateway.class);
    private final FindPostData findPostData = Mockito.mock(FindPostData.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldExecuteADeleteAPostDataWithSuccess() {

        Long id = new Random().nextLong();
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        Mockito.when(findPostData.execute(id)).thenReturn(dataPostMock);
        Mockito.doNothing().when(dataPostGateway).deleteById(id);

        DeletePostData deletePostData = new DeletePostData(dataPostGateway, findPostData);
        deletePostData.execute(id);

        Mockito.verify(dataPostGateway, Mockito.times(1)).deleteById(id);

    }

    @Test
    public void shouldExecuteADeleteAPostDataNotCallingDeleteByIdWithSuccess() {

        Long id = new Random().nextLong();
        Mockito.when(findPostData.execute(id)).thenReturn(null);
        Mockito.doNothing().when(dataPostGateway).deleteById(id);

        DeletePostData deletePostData = new DeletePostData(dataPostGateway, findPostData);
        deletePostData.execute(id);

        Mockito.verify(dataPostGateway, Mockito.times(0)).deleteById(id);

    }

    @Test
    public void shouldThrowAnErrorWhenExecuteADeleteAPostData() {

        Long id = new Random().nextLong();
        Mockito.when(findPostData.execute(id)).thenThrow(new RuntimeException("Fail to delete a post data test"));
        Mockito.doNothing().when(dataPostGateway).deleteById(id);

        DeletePostData deletePostData = new DeletePostData(dataPostGateway, findPostData);
        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> deletePostData.execute(id));

        Mockito.verify(dataPostGateway, Mockito.times(0)).deleteById(id);
        assertNotNull(exception);
        assertEquals(MessageInfo.DELETE_POST_DATA_FAIL.replace("%s", "") + "Fail to delete a post data test", exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());

    }

    @Test
    public void shouldThrowAnErrorWhenExecuteADeleteAPostDataWhitValidFindPostDataReturned() {

        Long id = new Random().nextLong();
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        Mockito.when(findPostData.execute(id)).thenReturn(dataPostMock);
        Mockito.doThrow(new RuntimeException("Fail to delete a post data gateway test")).when(dataPostGateway).deleteById(id);

        DeletePostData deletePostData = new DeletePostData(dataPostGateway, findPostData);
        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> deletePostData.execute(id));

        Mockito.verify(dataPostGateway, Mockito.times(1)).deleteById(id);
        assertNotNull(exception);
        assertEquals(MessageInfo.DELETE_POST_DATA_FAIL.replace("%s", "") + "Fail to delete a post data gateway test", exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());

    }

}
