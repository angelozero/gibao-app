package com.angelozero.gibao.front.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPost;
import com.angelozero.gibao.app.usecase.datapost.FindDataPost;
import com.angelozero.gibao.app.usecase.datapost.GetRandomExcuse;
import com.angelozero.gibao.app.usecase.datapost.SaveDataPost;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomNumber;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DataPostControllerTest {

    private static final long ID = 1L;
    private final SaveDataPost saveDataPost = Mockito.mock(SaveDataPost.class);
    private final DeleteDataPost deleteDataPost = Mockito.mock(DeleteDataPost.class);
    private final FindDataPost findDataPost = Mockito.mock(FindDataPost.class);
    private final GetPokemonByRandomNumber getPokemonByRandomNumber = Mockito.mock(GetPokemonByRandomNumber.class);
    private final GetRandomExcuse getRandomExcuse = Mockito.mock(GetRandomExcuse.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldCallHomeWithSuccess() {
        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);

        assertEquals("redirect:/gibao-app/data", controller.index());
    }

    @Test
    public void shouldCallDataPostWithSuccess() {
        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        Mockito.when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        Mockito.when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);
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

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);
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
        Mockito.when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());
        Mockito.when(findDataPost.execute()).thenReturn(dataPostMock);

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);
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

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);
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

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);
        ModelAndView modelAndView = controller.getDataPostDetail(ID);

        assertNotNull(modelAndView);
        assertNotNull(modelAndView.getModel().get("infoDataPost"));
    }

    @Test
    public void shouldCallDataPostDetailJsonWithSuccess() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.when(findDataPost.execute(ID)).thenReturn(dataPostMock);

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);
        ResponseEntity<Map<String, Object>> response = controller.getDataPostDetailJson(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("infoDataPost"));
    }

    @Test
    public void shouldCallDataPostFormWithSuccess() {
        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);

        assertEquals("dataPostForm", controller.getDataPostForm());
    }

    @Test
    public void shouldCallSaveDataPostWithSuccessNullBidingResult() {
        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);

        assertEquals("redirect:/gibao-app/data", controller.saveDataPost(dataPostRequest, null));
    }

    @Test
    public void shouldCallSaveDataPostWithSuccessErrorBidingResult() {
        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResultMock.hasErrors()).thenReturn(Boolean.TRUE);
        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);

        assertEquals("redirect:/gibao-app/new-data", controller.saveDataPost(dataPostRequest, bindingResultMock));
    }

    @Test
    public void shouldCallSaveDataPostWithSuccess() {
        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResultMock.hasErrors()).thenReturn(Boolean.FALSE);
        Mockito.doNothing().when(saveDataPost).execute(Mockito.any(DataPost.class));
        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);

        assertEquals("redirect:/gibao-app/data", controller.saveDataPost(dataPostRequest, bindingResultMock));
    }

    @Test
    public void shouldCallDeleteDataPostWithSuccess() {
        Mockito.doNothing().when(deleteDataPost).execute(ID);

        DataPostController controller = new DataPostController(saveDataPost, deleteDataPost, findDataPost, getPokemonByRandomNumber, getRandomExcuse);

        assertEquals("redirect:/gibao-app/data", controller.deleteDataPost(ID));
    }

}
