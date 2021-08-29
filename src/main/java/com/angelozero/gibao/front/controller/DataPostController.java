package com.angelozero.gibao.front.controller;


import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.*;
import com.angelozero.gibao.front.controller.mapper.DataPostRequestMapper;
import com.angelozero.gibao.front.controller.rest.DataPostRequest;

import com.angelozero.gibao.front.controller.rest.DataPostResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/gibao-app")
@AllArgsConstructor
@EnableCaching
public class DataPostController {

    private final SaveDataPost saveDataPost;
    private final DeleteDataPost deleteDataPost;
    private final FindDataPost findDataPost;
    private final GetPokemonByNumber getPokemonByNumber;
    private final GetRandomExcuse getRandomExcuse;


    /**
     * GET Data
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ModelAndView getDataPost() {
        ModelAndView modelAndView = new ModelAndView("infoDataPostView");
        modelAndView.addObject("pokemon", getPokemonByNumber.execute());
        modelAndView.addObject("description", getRandomExcuse.execute());
        return modelAndView;
    }

    @RequestMapping(value = "/data/json", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDataPostJson() {
        Map<String, Object> itens = new HashMap<>();
        itens.put("pokemon", getPokemonByNumber.execute());
        itens.put("description", getRandomExcuse.execute());
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * GET Data List
     */
    @RequestMapping(value = "/data/list", method = RequestMethod.GET)
    public ModelAndView getDataPostList() {
        List<DataPostResponse> dataPostResponseList = DataPostRequestMapper.toDataPostResponseList(findDataPost.execute());
        ModelAndView modelAndView = new ModelAndView("infoDataPostListView");
        modelAndView.addObject("pokemon", getPokemonByNumber.execute());
        modelAndView.addObject("infoDataPostList", dataPostResponseList);
        return modelAndView;
    }

    @RequestMapping(value = "/data/list/json", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDataPostListJson() {
        List<DataPostResponse> dataPostResponseList = DataPostRequestMapper.toDataPostResponseList(findDataPost.execute());
        Map<String, Object> itens = new HashMap<>();
        itens.put("pokemon", getPokemonByNumber.execute());
        itens.put("dataPostList", dataPostResponseList);
        itens.put("description", getRandomExcuse.execute());
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * GET Data by ID
     */
    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    public ModelAndView getDataPostDetail(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("infoDataPostDetailView");
        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(findDataPost.execute(id));
        return mv.addObject("infoDataPost", dataPostResponse);
    }

    @RequestMapping(value = "/data/{id}/json", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDataPostDetailJson(@PathVariable("id") long id) {
        Map<String, Object> itens = new HashMap<>();
        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(findDataPost.execute(id));
        itens.put("infoDataPost", dataPostResponse);
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * SAVE Data
     */
    @RequestMapping(value = "/new-data", method = RequestMethod.POST)
    public String saveDataPost(@Valid DataPostRequest dataPostRequest, BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return "redirect:/gibao-app/new-data";
        }
        DataPost dataPost = DataPostRequestMapper.toDataPost(dataPostRequest);
        saveDataPost.execute(dataPost);
        return redirectIndexPage();
    }

    /**
     * DELETE Data
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteDataPost(long id) {
        deleteDataPost.execute(id);
        return redirectIndexPage();
    }

    /**
     * REDIRECT Route
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return redirectIndexPage();
    }

    @RequestMapping(value = "/new-data", method = RequestMethod.GET)
    public String getDataPostForm() {
        return "dataPostForm";
    }

    private static String redirectIndexPage() {
        return "redirect:/gibao-app/data";
    }
}
