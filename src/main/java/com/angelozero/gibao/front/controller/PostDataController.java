package com.angelozero.gibao.front.controller;


import com.angelozero.gibao.app.usecase.DeletePostData;
import com.angelozero.gibao.app.usecase.FindPostsData;
import com.angelozero.gibao.app.usecase.SavePostData;
import com.angelozero.gibao.front.controller.mapper.PostDataMapper;
import com.angelozero.gibao.front.controller.rest.PostDataRequest;
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

    private final SavePostData savePostData;
    private final DeletePostData deletePostData;
    private final FindPostsData findPostsData;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return redirectIndexPage();
    }

    //@RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET)
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getInfoPost() {
        ModelAndView mv = new ModelAndView("infoPostView");
        return mv.addObject("infoPostList", findPostsData.execute());
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getInfoPostDetail(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("infoPostDetailView");
        return mv.addObject("infoPost", findPostsData.execute(id));
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm() {
        return "postForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid PostDataRequest postDataRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/newpost";
        }
        savePostData.execute(PostDataMapper.toPostData(postDataRequest));
        return redirectIndexPage();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(long id) {
        deletePostData.execute(id);
        return redirectIndexPage();
    }

    private static String redirectIndexPage() {
        return "redirect:/posts";
    }
}
