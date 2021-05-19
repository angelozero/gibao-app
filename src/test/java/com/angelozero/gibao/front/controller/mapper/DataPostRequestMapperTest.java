package com.angelozero.gibao.front.controller.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.util.MessageInfo;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;
import com.angelozero.gibao.front.controller.rest.PostDataResponse;
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

        DataPost dataPost = DataPostRequestMapper.toPostData(dataPostRequestMock);

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

        MapperException exception = assertThrows(MapperException.class, () -> DataPostRequestMapper.toPostData(dataPostRequestMock));

        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(MessageInfo.DATA_POST_REQUEST_MAPPER_ERROR_NULL_AUTHOR, exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldConvertADataPostRequestToDataPostWithErrorNullData() {
        MapperException exception = assertThrows(MapperException.class, () -> DataPostRequestMapper.toPostData(null));

        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
        assertEquals(MessageInfo.DATA_POST_REQUEST_MAPPER_ERROR_NULL_DATA_POST, exception.getError().getMessage());
        assertNull(exception.getError().getIdentifier());
    }

    @Test
    public void shouldConvertADataPostToDataPostResponseWithSuccess() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        PostDataResponse postDataResponse = DataPostRequestMapper.toPostDataResponse(dataPostMock);

        assertNotNull(postDataResponse);
        assertNotNull(postDataResponse.getAuthor());
        assertEquals(dataPostMock.getAuthor().getName(), postDataResponse.getAuthor().getName());
        assertEquals(dataPostMock.getId(), postDataResponse.getId());
        assertEquals(dataPostMock.getTitle(), postDataResponse.getTitle());
        assertEquals(dataPostMock.getDescription(), postDataResponse.getDescription());
        assertEquals(dataPostMock.getSecretUser(), postDataResponse.getSecretUser());
    }

    @Test
    public void shouldConvertADataPostToDataPostResponseWithSuccessNoAuthor() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without Author");

        PostDataResponse postDataResponse = DataPostRequestMapper.toPostDataResponse(dataPostMock);

        assertNotNull(postDataResponse);
        assertNotNull(postDataResponse.getAuthor());
        assertNull(postDataResponse.getAuthor().getName());
        assertEquals(dataPostMock.getId(), postDataResponse.getId());
        assertEquals(dataPostMock.getTitle(), postDataResponse.getTitle());
        assertEquals(dataPostMock.getDescription(), postDataResponse.getDescription());
        assertEquals(dataPostMock.getSecretUser(), postDataResponse.getSecretUser());
    }

    @Test
    public void shouldConvertADataPostToDataPostResponseWithSuccessNoData() {

        PostDataResponse postDataResponse = DataPostRequestMapper.toPostDataResponse(null);

        assertNotNull(postDataResponse);
        assertNull(postDataResponse.getAuthor());
    }

    @Test
    public void shouldConvertAListOfDataPostToAListOfDataPostResponseWithSuccess() {
        List<DataPost> dataPostListMock = Fixture.from(DataPost.class).gimme(3, "valid DataPost");
        List<PostDataResponse> postDataResponseList = DataPostRequestMapper.toPostDataList(dataPostListMock);

        assertNotNull(postDataResponseList);
        assertEquals(3, postDataResponseList.size());
    }

    @Test
    public void shouldConvertAListOfDataPostToAListOfDataPostResponseWithSuccessNullList() {
        List<PostDataResponse> postDataResponseList = DataPostRequestMapper.toPostDataList(null);

        assertNotNull(postDataResponseList);
        assertEquals(0, postDataResponseList.size());
    }
}
