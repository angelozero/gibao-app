package com.angelozero.gibao.app.gateway.db.postgres;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DataPostGatewayPostgresImplTest {

    private final DataPostJPARepository dataPostJPARepository = Mockito.mock(DataPostJPARepository.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldSaveAPostDataWithSuccess() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");
        DataPostModel dataPostModelMock = Fixture.from(DataPostModel.class).gimme("valid DataPostModel");

        Mockito.when(dataPostJPARepository.save(Mockito.any(DataPostModel.class))).thenReturn(dataPostModelMock);
        DataPostGatewayPostgresImpl dataPostGatewayPostgresImpl = new DataPostGatewayPostgresImpl(dataPostJPARepository, null);

        DataPost dataPost = dataPostGatewayPostgresImpl.save(dataPostMock);

        assertNotNull(dataPost);
    }

    @Test
    public void shouldDeleteWithSuccessAPostDataWithSuccess() {
        Long id = new Random().nextLong();
        Mockito.doNothing().when(dataPostJPARepository).deleteById(id);
        DataPostGatewayPostgresImpl dataPostGatewayPostgresImpl = new DataPostGatewayPostgresImpl(dataPostJPARepository, null);

        dataPostGatewayPostgresImpl.deleteById(id);

        Mockito.verify(dataPostJPARepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void shouldFindAllPostDataWithSuccess() {
        List<DataPostModel> dataPostModelList = Fixture.from(DataPostModel.class).gimme(3, "valid DataPostModel");

        Mockito.when(dataPostJPARepository.findAll()).thenReturn(dataPostModelList);
        DataPostGatewayPostgresImpl dataPostGatewayPostgresImpl = new DataPostGatewayPostgresImpl(dataPostJPARepository, null);

        List<DataPost> dataPostList = dataPostGatewayPostgresImpl.findAll();

        assertNotNull(dataPostList);
        assertFalse(dataPostList.isEmpty());
        assertEquals(3, dataPostList.size());
    }
}
