package com.angelozero.gibao.front.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.DeleteDataPost;
import com.angelozero.gibao.app.usecase.FindDataPost;
import com.angelozero.gibao.app.usecase.GetPokemonByNumber;
import com.angelozero.gibao.app.usecase.SaveDataPost;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostDataControllerTest {

    private static final long ID = 1L;
    private final SaveDataPost saveDataPost = Mockito.mock(SaveDataPost.class);
    private final DeleteDataPost deleteDataPost = Mockito.mock(DeleteDataPost.class);
    private final FindDataPost findDataPost = Mockito.mock(FindDataPost.class);
    private final GetPokemonByNumber getPokemonByNumber = Mockito.mock(GetPokemonByNumber.class);

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @Test
    public void shouldCallHomeWithSuccess() {
        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);

        assertEquals("redirect:/posts", controller.index());
    }

    @Test
    public void shouldCallInfoPostWithSuccess() {
        List<DataPost> dataPostMock = Fixture.from(DataPost.class).gimme(3, "valid DataPost");

        Mockito.when(getPokemonByNumber.execute()).thenReturn("pokemon test");
        Mockito.when(findDataPost.execute()).thenReturn(dataPostMock);

        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);
        ModelAndView modelAndView = controller.getInfoPost();

        assertNotNull(modelAndView);
        assertEquals("pokemon test", modelAndView.getModel().get("pokemon"));
        assertNotNull(modelAndView.getModel().get("infoPostList"));
    }

    @Test
    public void shouldCallInfoPostDetailWithSuccess() {
        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost");

        Mockito.when(findDataPost.execute(ID)).thenReturn(dataPostMock);

        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);
        ModelAndView modelAndView = controller.getInfoPostDetail(ID);

        assertNotNull(modelAndView);
        assertNotNull(modelAndView.getModel().get("infoPost"));
    }

    @Test
    public void shouldCallPostFormWithSuccess() {
        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);

        assertEquals("postForm", controller.getPostForm());
    }

    @Test
    public void shouldCallSavePostWithSuccessNullBidingResult() {
        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);

        assertEquals("redirect:/posts", controller.savePost(dataPostRequest, null));
    }

    @Test
    public void shouldCallSavePostWithSuccessErrorBidingResult() {
        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResultMock.hasErrors()).thenReturn(Boolean.TRUE);
        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);

        assertEquals("redirect:/newpost", controller.savePost(dataPostRequest, bindingResultMock));
    }

    @Test
    public void shouldCallSavePostWithSuccess() {
        DataPostRequest dataPostRequest = Fixture.from(DataPostRequest.class).gimme("valid DataPostRequest");
        BindingResult bindingResultMock = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResultMock.hasErrors()).thenReturn(Boolean.FALSE);
        Mockito.doNothing().when(saveDataPost).execute(Mockito.any(DataPost.class));
        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);

        assertEquals("redirect:/posts", controller.savePost(dataPostRequest, bindingResultMock));
    }

    @Test
    public void shouldCallDeletePostWithSuccess() {
        Mockito.doNothing().when(deleteDataPost).execute(ID);

        PostDataController controller = new PostDataController(saveDataPost, deleteDataPost, findDataPost, getPokemonByNumber);

        assertEquals("redirect:/posts", controller.deletePost(ID));
    }

}
