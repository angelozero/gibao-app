package com.angelozero.gibao.app.gateway.db.postgres;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@MockitoSettings
class DataPostGatewayPostgresImplTest {

    @Mock
    private DataPostJPARepository dataPostJPARepository;

    @InjectMocks
    private DataPostGatewayPostgresImpl dataPostGatewayPostgresImpl;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    @DisplayName("Should save a post data with success")
    void shouldSaveAPostDataWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        DataPostModel dataPostModelMock = Fixture.from(DataPostModel.class).gimme("valid DataPostModel");

        when(dataPostJPARepository.save(any(DataPostModel.class))).thenReturn(dataPostModelMock);

        DataPost dataPost = dataPostGatewayPostgresImpl.save(dataPostMock);

        assertNotNull(dataPost);
    }

    @Test
    @DisplayName("Should delete a post data with success")
    void shouldDeleteWithSuccessAPostDataWithSuccess() {

        Long id = new Random().nextLong();
        doNothing().when(dataPostJPARepository).deleteById(id);

        dataPostGatewayPostgresImpl.deleteById(id);

        verify(dataPostJPARepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should final all posts data with success")
    void shouldFindAllPostDataWithSuccess() {

        List<DataPostModel> dataPostModelList = Fixture.from(DataPostModel.class).gimme(3, "valid DataPostModel");

        when(dataPostJPARepository.findAll()).thenReturn(dataPostModelList);

        List<DataPost> dataPostList = dataPostGatewayPostgresImpl.findAll();

        assertNotNull(dataPostList);
        assertFalse(dataPostList.isEmpty());
        assertEquals(3, dataPostList.size());
    }
}
