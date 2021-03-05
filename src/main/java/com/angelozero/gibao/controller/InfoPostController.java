package com.angelozero.gibao.controller;


import com.angelozero.gibao.dao.InfoPost;
import com.angelozero.gibao.service.InfoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class InfoPostController {

    @Autowired
    InfoPostService infoPostService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getInfoPost() {
        ModelAndView mv = new ModelAndView("infoPostView");
        return mv.addObject("infoPostList", infoPostService.findaAll());
    }
}
