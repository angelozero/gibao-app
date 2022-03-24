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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MockitoSettings
class DataPostControllerTest {

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

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    @DisplayName("Should call home with success")
    void shouldCallHomeWithSuccess() {

        assertEquals("redirect:/gibao-app/data", controller.index());
    }

    @Test
    @DisplayName("Should call data post with success")
    void shouldCallDataPostWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());

        ModelAndView modelAndView = controller.getDataPost();

        assertNotNull(modelAndView);
        assertEquals("pokemon test", modelAndView.getModel().get("pokemon"));
        assertEquals(dataPostMock.get(0).getDescription(), modelAndView.getModel().get("description"));
    }

    @Test
    @DisplayName("Should call data post json with success")
    void shouldCallDataPostJsonWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());

        ResponseEntity<Map<String, Object>> response = controller.getDataPostJson();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("pokemon test", response.getBody().get("pokemon"));
        assertEquals(dataPostMock.get(0).getDescription(), response.getBody().get("description"));
    }

    @Test
    @DisplayName("Should call data post list with success")
    void shouldCallDataPostListWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        lenient().when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());
        when(findDataPost.execute()).thenReturn(dataPostMock);

        ModelAndView modelAndView = controller.getDataPostList();

        assertNotNull(modelAndView);
        assertEquals("pokemon test", modelAndView.getModel().get("pokemon"));
        assertNotNull(modelAndView.getModel().get("infoDataPostList"));
    }

    @Test
    @DisplayName("Should call data post list json with success")
    void shouldCallDataPostListJsonWithSuccess() {

        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(1, "valid DataPost");

        when(getPokemonByRandomNumber.execute()).thenReturn("pokemon test");
        when(getRandomExcuse.execute()).thenReturn(dataPostMock.get(0).getDescription());
        when(findDataPost.execute()).thenReturn(dataPostMock);

        ResponseEntity<Map<String, Object>> response = controller.getDataPostListJson();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("pokemon test", response.getBody().get("pokemon"));
        assertNotNull(response.getBody().get("dataPostList"));
        assertEquals(dataPostMock.get(0).getDescription(), response.getBody().get("description"));
    }

    @Test
    @DisplayName("Should call data post detail with success")
    void shouldCallDataPostDetailWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        when(findDataPost.execute(ID)).thenReturn(dataPostMock);

        ModelAndView modelAndView = controller.getDataPostDetail(ID);

        assertNotNull(modelAndView);
        assertNotNull(modelAndView.getModel().get("infoDataPost"));
    }

    @Test
    @DisplayName("Should call data post detail json with success")
    void shouldCallDataPostDetailJsonWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        when(findDataPost.execute(ID)).thenReturn(dataPostMock);

        ResponseEntity<Map<String, Object>> response = controller.getDataPostDetailJson(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("infoDataPost"));
    }

    @Test
    @DisplayName("Should call data post form with success")
    void shouldCallDataPostFormWithSuccess() {

        assertEquals("dataPostForm", controller.getDataPostForm());
    }

    @Test
    @DisplayName("Should call data post with success null binding result")
    void shouldCallSaveDataPostWithSuccessNullBidingResult() {

        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");

        assertEquals("redirect:/gibao-app/data", controller.saveDataPost(dataPostRequest, null));
    }

    @Test
    @DisplayName("Should call save data post with success error biding result")
    void shouldCallSaveDataPostWithSuccessErrorBidingResult() {

        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = mock(BindingResult.class);

        when(bindingResultMock.hasErrors()).thenReturn(Boolean.TRUE);

        assertEquals("redirect:/gibao-app/new-data", controller.saveDataPost(dataPostRequest, bindingResultMock));
    }

    @Test
    @DisplayName("Should call save data post with success")
    void shouldCallSaveDataPostWithSuccess() {

        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = mock(BindingResult.class);

        when(bindingResultMock.hasErrors()).thenReturn(Boolean.FALSE);
        doNothing().when(saveDataPost).execute(any(DataPost.class));

        assertEquals("redirect:/gibao-app/data", controller.saveDataPost(dataPostRequest, bindingResultMock));
    }

    @Test
    @DisplayName("Should call delete data post with success")
    void shouldCallDeleteDataPostWithSuccess() {

        doNothing().when(deleteDataPost).execute(ID);

        assertEquals("redirect:/gibao-app/data", controller.deleteDataPost(ID));
    }

    @Test
    @DisplayName("Should call async with success")
    void shouldCallAsyncApiPokemon() {

        when(getPokemonsByRangeNumber.execute(anyInt(), anyInt())).thenReturn(List.of("Success"));

        assertEquals(List.of("Success"), controller.getDataPostAsyncJson(1, 10).getBody());
    }
}
