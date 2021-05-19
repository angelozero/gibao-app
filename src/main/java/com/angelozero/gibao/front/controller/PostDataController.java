package com.angelozero.gibao.front.controller;



import com.angelozero.gibao.app.usecase.DeleteDataPost;
import com.angelozero.gibao.app.usecase.FindDataPost;
import com.angelozero.gibao.app.usecase.GetPokemonByNumber;
import com.angelozero.gibao.app.usecase.SaveDataPost;
import com.angelozero.gibao.front.controller.mapper.DataPostRequestMapper;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class PostDataController {


    private final SaveDataPost saveDataPost;
    private final DeleteDataPost deleteDataPost;
    private final FindDataPost findDataPost;
    private final GetPokemonByNumber getPokemonByNumber;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return redirectIndexPage();
    }

    //@RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET)
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getInfoPost() {
        ModelAndView modelAndView = new ModelAndView("infoPostView");

        modelAndView.addObject("pokemon", getPokemonByNumber.execute());
        modelAndView.addObject("infoPostList", findDataPost.execute());

        return modelAndView;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getInfoPostDetail(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("infoPostDetailView");
        return mv.addObject("infoPost", DataPostRequestMapper.toPostDataResponse(findDataPost.execute(id)));
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm() {
        return "postForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid DataPostRequest dataPostRequest, BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return "redirect:/newpost";
        }
        saveDataPost.execute(DataPostRequestMapper.toPostData(dataPostRequest));
        return redirectIndexPage();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(long id) {
        deleteDataPost.execute(id);
        return redirectIndexPage();
    }

    private static String redirectIndexPage() {
        return "redirect:/posts";
    }
}
