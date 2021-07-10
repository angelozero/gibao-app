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

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FindDataPostTest {

    private final DataPostGateway dataPostGateway = Mockito.mock(DataPostGateway.class);
    private final RedisService<List<DataPost>> redisService = Mockito.mock(RedisService.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    public void shouldFindAListOfDataPosts() {
        List<DataPost> dataPostListMock = Fixture.from(com.angelozero.gibao.app.domain.DataPost.class).gimme(3, "valid DataPost");
        Mockito.when(redisService.findAll(Mockito.any())).thenReturn(Collections.emptyList());
        Mockito.when(dataPostGateway.findAll()).thenReturn(dataPostListMock);

        FindDataPost findDataPost = new FindDataPost(dataPostGateway, redisService);
        List<DataPost> dataPostList = findDataPost.execute();

        assertNotNull(dataPostList);
        assertFalse(dataPostList.isEmpty());
        assertEquals(3, dataPostList.size());
    }

    @Test
    public void shouldThrowAnExceptionWhenFindAListOfDataPosts() {
        Mockito.doThrow(new RuntimeException("Erro to get data post list test")).when(dataPostGateway).findAll();

        FindDataPost findDataPost = new FindDataPost(dataPostGateway, redisService);

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, findDataPost::execute);

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.FIND_DATA_POST_LIST_ERROR, "Erro to get data post list test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }


    @Test
    public void shouldFindADataPostById() {
        DataPost dataPostMock = Fixture.from(com.angelozero.gibao.app.domain.DataPost.class).gimme("valid DataPost");
        Mockito.when(dataPostGateway.findById(dataPostMock.getId())).thenReturn(dataPostMock);

        FindDataPost findDataPost = new FindDataPost(dataPostGateway, redisService);
        DataPost dataPost = findDataPost.execute(dataPostMock.getId());

        assertNotNull(dataPost);
    }

    @Test
    public void shouldThrowAnExceptionWhenFindADataPostById() {
        Long id = new Random().nextLong();
        Mockito.doThrow(new RuntimeException("Erro to get data post by id test")).when(dataPostGateway).findById(id);

        FindDataPost findDataPost = new FindDataPost(dataPostGateway, redisService);

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> findDataPost.execute(id));

        assertNotNull(exception);
        assertEquals(MessagesUtil.join(MessagesUtil.FIND_DATA_POST_ID_ERROR, "Erro to get data post by id test"), exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }


}
