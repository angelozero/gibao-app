package com.angelozero.gibao.front.controller.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.util.MessagesUtil;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;
import com.angelozero.gibao.front.controller.rest.DataPostResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataPostRequestMapperTest {

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    public void shouldConvertADataPostRequestToDataPostWithSuccess() {
        DataPostRequest dataPostRequestMock = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");

        DataPost dataPost = DataPostRequestMapper.toDataPost(dataPostRequestMock);

        assertNotNull(dataPost);
        assertNotNull(dataPost.getAuthor());
        assertEquals(dataPostRequestMock.getAuthor().getName(), dataPost.getAuthor().getName());
        assertEquals(dataPostRequestMock.getId(), dataPost.getId());
        assertEquals(dataPostRequestMock.getTitle(), dataPost.getTitle());
        assertEquals(dataPostRequestMock.getDescription(), dataPost.getDescription());
    }

    @Test
    public void shouldConvertADataPostRequestToDataPostWithErrorNoAuthor() {
        DataPostRequest dataPostRequestMock = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest without Author");

        MapperException exception = assertThrows(MapperException.class, () -> DataPostRequestMapper.toDataPost(dataPostRequestMock));

        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(MessagesUtil.DATA_POST_REQUEST_MAPPER_ERROR_NULL_AUTHOR, exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldConvertADataPostRequestToDataPostWithErrorNullData() {
        MapperException exception = assertThrows(MapperException.class, () -> DataPostRequestMapper.toDataPost(null));

        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(MessagesUtil.DATA_POST_REQUEST_MAPPER_ERROR_NULL_DATA_POST, exception.getError().getMessage());
        assertNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldConvertADataPostToDataPostResponseWithSuccess() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(dataPostMock);

        assertNotNull(dataPostResponse);
        assertNotNull(dataPostResponse.getAuthor());
        assertEquals(dataPostMock.getAuthor().getName(), dataPostResponse.getAuthor().getName());
        assertEquals(dataPostMock.getId(), dataPostResponse.getId());
        assertEquals(dataPostMock.getTitle(), dataPostResponse.getTitle());
        assertEquals(dataPostMock.getDescription(), dataPostResponse.getDescription());
        assertEquals(dataPostMock.getSecretUser(), dataPostResponse.getSecretUser());
    }

    @Test
    public void shouldConvertADataPostToDataPostResponseWithSuccessNoAuthor() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without Author");

        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(dataPostMock);

        assertNotNull(dataPostResponse);
        assertNotNull(dataPostResponse.getAuthor());
        assertNull(dataPostResponse.getAuthor().getName());
        assertEquals(dataPostMock.getId(), dataPostResponse.getId());
        assertEquals(dataPostMock.getTitle(), dataPostResponse.getTitle());
        assertEquals(dataPostMock.getDescription(), dataPostResponse.getDescription());
        assertEquals(dataPostMock.getSecretUser(), dataPostResponse.getSecretUser());
        assertNotNull(dataPostResponse.getInfoDate());
    }

    @Test
    public void shouldConvertADataPostToDataPostResponseWithSuccessNoData() {

        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(null);

        assertNotNull(dataPostResponse);
        assertNull(dataPostResponse.getAuthor());
    }

    @Test
    public void shouldConvertAListOfDataPostToAListOfDataPostResponseWithSuccess() {
        List<DataPost> dataPostListMock = Fixture.from(DataPost.class).gimme(3, "valid DataPost");
        List<DataPostResponse> dataPostResponseList = DataPostRequestMapper.toDataPostResponseList(dataPostListMock);

        assertNotNull(dataPostResponseList);
        assertEquals(3, dataPostResponseList.size());
    }

    @Test
    public void shouldConvertAListOfDataPostToAListOfDataPostResponseWithSuccessNullList() {
        List<DataPostResponse> dataPostResponseList = DataPostRequestMapper.toDataPostResponseList(null);

        assertNotNull(dataPostResponseList);
        assertEquals(0, dataPostResponseList.size());
    }
}
