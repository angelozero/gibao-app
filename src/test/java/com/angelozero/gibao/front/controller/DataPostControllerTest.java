package com.angelozero.gibao.front.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPost;
import com.angelozero.gibao.app.usecase.datapost.FindDataPost;
import com.angelozero.gibao.app.usecase.datapost.GetRandomExcuse;
import com.angelozero.gibao.app.usecase.datapost.SaveDataPost;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomNumber;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonsByRangeNumber;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DataPostControllerTest {

    private static final long ID = 1L;

    @Mock
    private SaveDataPost saveDataPost;

    @Mock
    private DeleteDataPost deleteDataPost;

    @Mock
    private FindDataPost findDataPost;

    @Mock
    private GetPokemonByRandomNumber getPokemonByRandomNumber;

    @Mock
    private GetRandomExcuse getRandomExcuse;

    @Mock
    private GetPokemonsByRangeNumber getPokemonsByRangeNumber;

    @InjectMocks
    private DataPostController controller;

    @BeforeClass
    public static void setup() {

        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldCallHomeWithSuccess() {

        assertEquals("redirect:/gibao-app/data", controller.index());
    }

    @Test
    public void shouldCallDataPostWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        Mockito.when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        Mockito.when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());

        ModelAndView modelAndView = controller.getDataPost();

        assertNotNull(modelAndView);
        assertEquals("pokemon test", modelAndView.getModel().get("pokemon"));
        assertEquals(dataPostMock.get(0).getDescription(), modelAndView.getModel().get("description"));
    }

    @Test
    public void shouldCallDataPostJsonWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        Mockito.when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        Mockito.when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());

        ResponseEntity<Map<String, Object>> response = controller.getDataPostJson();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("pokemon test", response.getBody().get("pokemon"));
        assertEquals(dataPostMock.get(0).getDescription(), response.getBody().get("description"));
    }

    @Test
    public void shouldCallDataPostListWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        Mockito.when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        Mockito.lenient().when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());
        Mockito.when(findDataPost.execute()).thenReturn(dataPostMock);

        ModelAndView modelAndView = controller.getDataPostList();

        assertNotNull(modelAndView);
        assertEquals("pokemon test", modelAndView.getModel().get("pokemon"));
        assertNotNull(modelAndView.getModel().get("infoDataPostList"));
    }

    @Test
    public void shouldCallDataPostListJsonWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        Mockito.when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        Mockito.when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());
        Mockito.when(findDataPost.execute()).thenReturn(dataPostMock);

        ResponseEntity<Map<String, Object>> response = controller.getDataPostListJson();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("pokemon test", response.getBody().get("pokemon"));
        assertNotNull(response.getBody().get("dataPostList"));
        assertEquals(dataPostMock.get(0).getDescription(), response.getBody().get("description"));
    }

    @Test
    public void shouldCallDataPostDetailWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.when(findDataPost.execute(ID)).thenReturn(dataPostMock);

        ModelAndView modelAndView = controller.getDataPostDetail(ID);

        assertNotNull(modelAndView);
        assertNotNull(modelAndView.getModel().get("infoDataPost"));
    }

    @Test
    public void shouldCallDataPostDetailJsonWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.when(findDataPost.execute(ID)).thenReturn(dataPostMock);

        ResponseEntity<Map<String, Object>> response = controller.getDataPostDetailJson(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("infoDataPost"));
    }

    @Test
    public void shouldCallDataPostFormWithSuccess() {

        assertEquals("dataPostForm", controller.getDataPostForm());
    }

    @Test
    public void shouldCallSaveDataPostWithSuccessNullBidingResult() {

        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");

        assertEquals("redirect:/gibao-app/data", controller.saveDataPost(dataPostRequest, null));
    }

    @Test
    public void shouldCallSaveDataPostWithSuccessErrorBidingResult() {

        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResultMock.hasErrors()).thenReturn(Boolean.TRUE);

        assertEquals("redirect:/gibao-app/new-data", controller.saveDataPost(dataPostRequest, bindingResultMock));
    }

    @Test
    public void shouldCallSaveDataPostWithSuccess() {

        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResultMock.hasErrors()).thenReturn(Boolean.FALSE);
        Mockito.doNothing().when(saveDataPost).execute(Mockito.any(DataPost.class));

        assertEquals("redirect:/gibao-app/data", controller.saveDataPost(dataPostRequest, bindingResultMock));
    }

    @Test
    public void shouldCallDeleteDataPostWithSuccess() {

        Mockito.doNothing().when(deleteDataPost).execute(ID);

        assertEquals("redirect:/gibao-app/data", controller.deleteDataPost(ID));
    }

    @Test
    public void shouldCallAsyncApiPokemon() {

        Mockito.when(getPokemonsByRangeNumber.execute(Mockito.anyInt(), Mockito.anyInt())).thenReturn(List.of("Success"));

        assertEquals(List.of("Success"), controller.getDataPostAsyncJson(1, 10).getBody());
    }
}
