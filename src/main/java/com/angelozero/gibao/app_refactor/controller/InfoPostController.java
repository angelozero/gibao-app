package com.angelozero.gibao.app_refactor.controller;


import com.angelozero.gibao.app_refactor.dao.InfoPost;
import com.angelozero.gibao.app_refactor.service.InfoPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class InfoPostController {

    InfoPostService infoPostService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return redirectIndexPage();
    }

    //@RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET)
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getInfoPost() {
        ModelAndView mv = new ModelAndView("infoPostView");
        return mv.addObject("infoPostList", infoPostService.findaAll());
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getInfoPostDetail(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("infoPostDetailView");
        return mv.addObject("infoPost", infoPostService.findyById(id));
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm() {
        return "postForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid InfoPost infoPost, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("validationMessage", "Verifique se os campos obrigatórios foram preenchidos!");
            return "redirect:/newpost";
        }
        infoPostService.save(infoPost);
        return redirectIndexPage();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(long id) {
        infoPostService.delete(id);
        return redirectIndexPage();
    }

    private static String redirectIndexPage() {
        return "redirect:/posts";
    }
}
