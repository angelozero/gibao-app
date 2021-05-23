package com.angelozero.gibao.app.gateway.db.postgres.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.MapperException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DataPostModelMapperTest {

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    // toPostDataModel
    @Test
    public void shouldConvertAPostDataToPostDataModelWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        DataPostModel dataPostModel = DataPostModelMapper.toPostDataModel(dataPostMock);

        assertNotNull(dataPostModel);
        assertNotNull(dataPostModel.getAuthor());
        assertEquals(dataPostMock.getAuthor().getName(), dataPostModel.getAuthor());
        assertNotNull(dataPostModel.getDate());
        assertEquals(dataPostMock.getDescription(), dataPostModel.getDescription());
        assertEquals(dataPostMock.getId(), dataPostModel.getId());
        assertEquals(dataPostMock.getTitle(), dataPostModel.getTitle());
        assertEquals(dataPostMock.getSecretUser(), dataPostModel.getSecretUser());

    }

    @Test
    public void shouldConvertAPostDataWithoutAuthorToPostDataModelWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without Author");
        DataPostModel dataPostModel = DataPostModelMapper.toPostDataModel(dataPostMock);

        assertNotNull(dataPostModel);
        assertEquals(dataPostMock.getInfoDate(), dataPostModel.getDate().toString());
        assertTrue(StringUtils.isEmpty(dataPostModel.getAuthor()));
        assertEquals(dataPostMock.getDescription(), dataPostModel.getDescription());
        assertEquals(dataPostMock.getId(), dataPostModel.getId());
        assertEquals(dataPostMock.getTitle(), dataPostModel.getTitle());
        assertEquals(dataPostMock.getSecretUser(), dataPostModel.getSecretUser());
    }

    @Test
    public void shouldConvertAPostDataWithoutSecretUserToPostDataModelWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");
        DataPostModel dataPostModel = DataPostModelMapper.toPostDataModel(dataPostMock);

        assertNotNull(dataPostModel);
        assertNotNull(dataPostModel.getAuthor());
        assertEquals(dataPostMock.getAuthor().getName(), dataPostModel.getAuthor());
        assertEquals(dataPostMock.getInfoDate(), dataPostModel.getDate().toString());
        assertEquals(dataPostMock.getDescription(), dataPostModel.getDescription());
        assertEquals(dataPostMock.getId(), dataPostModel.getId());
        assertEquals(dataPostMock.getTitle(), dataPostModel.getTitle());
        assertFalse(dataPostModel.getSecretUser());
    }

    @Test
    public void shouldThrowAnExceptionWhenConvertANullPostDataModel() {

        MapperException exception = assertThrows(MapperException.class, () -> DataPostModelMapper.toPostDataModel(null));

        assertNotNull(exception);
        assertEquals(MessagesUtil.DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_DATA_MODEL, exception.getError().getMessage());
        assertNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }

    //toPostData
    @Test
    public void shouldConvertAPostDataModelToPostDataWithSuccess() {
        DataPostModel dataPostModelMock = Fixture.from(DataPostModel.class).gimme("valid DataPostModel");
        DataPost dataPost = DataPostModelMapper.toPostData(dataPostModelMock);

        assertNotNull(dataPost);
        assertNotNull(dataPost.getInfoDate());
        assertEquals(dataPostModelMock.getDescription(), dataPost.getDescription());
        assertEquals(dataPostModelMock.getId(), dataPost.getId());
        assertEquals(dataPostModelMock.getAuthor(), dataPost.getAuthor().getName());
        assertEquals(dataPostModelMock.getTitle(), dataPost.getTitle());
        assertEquals(dataPostModelMock.getSecretUser(), dataPost.getSecretUser());

    }

    @Test
    public void shouldConvertAPostDataModelWhithoutSecretUserToPostDataWithSuccess() {
        DataPostModel dataPostModelMock = Fixture.from(DataPostModel.class).gimme("valid DataPostModel without SecretUser");
        DataPost dataPost = DataPostModelMapper.toPostData(dataPostModelMock);

        assertNotNull(dataPost);
        assertEquals(dataPostModelMock.getDate().toString(), dataPost.getInfoDate());
        assertEquals(dataPostModelMock.getDescription(), dataPost.getDescription());
        assertEquals(dataPostModelMock.getId(), dataPost.getId());
        assertNotNull(dataPost.getAuthor());
        assertEquals(dataPostModelMock.getAuthor(), dataPost.getAuthor().getName());
        assertEquals(dataPostModelMock.getTitle(), dataPost.getTitle());
        assertFalse(dataPost.getSecretUser());
    }

    @Test
    public void shouldThrowAnExceptionWhenConvertANullPostData() {

        MapperException exception = assertThrows(MapperException.class, () -> DataPostModelMapper.toPostData(null));

        assertNotNull(exception);
        assertEquals(MessagesUtil.DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_POST_DATA, exception.getError().getMessage());
        assertNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }

    //toPostDataList
    @Test
    public void shouldConvertAPostDataModelListToPostDataListWithSuccess() {
        List<DataPostModel> dataPostModelListMock = Fixture.from(DataPostModel.class).gimme(3, "valid DataPostModel");
        List<DataPost> dataPostList = DataPostModelMapper.toPostDataList(dataPostModelListMock);

        assertNotNull(dataPostList);
        assertFalse(dataPostList.isEmpty());
        assertEquals(3, dataPostList.size());
    }

    @Test
    public void shouldConvertAnEmptyPostDataModelListToPostDataListWithSuccess() {
        List<DataPost> dataPostList = DataPostModelMapper.toPostDataList(null);

        assertNotNull(dataPostList);
        assertTrue(dataPostList.isEmpty());
    }
}
