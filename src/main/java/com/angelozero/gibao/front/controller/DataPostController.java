package com.angelozero.gibao.front.controller;


import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.datapost.DeleteDataPost;
import com.angelozero.gibao.app.usecase.datapost.FindDataPost;
import com.angelozero.gibao.app.usecase.datapost.GetRandomExcuse;
import com.angelozero.gibao.app.usecase.datapost.SaveDataPost;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonByRandomNumber;
import com.angelozero.gibao.app.usecase.pokemon.GetPokemonsByRangeNumber;
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
import org.springframework.web.bind.annotation.*;
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
    private final GetPokemonByRandomNumber getPokemonByRandomNumber;
    private final GetRandomExcuse getRandomExcuse;
    private final GetPokemonsByRangeNumber getPokemonsByRangeNumber;

    /**
     * GET Data
     */
    @GetMapping("/data")
    public ModelAndView getDataPost() {
        ModelAndView modelAndView = new ModelAndView("infoDataPostView");
        modelAndView.addObject("pokemon", getPokemonByRandomNumber.execute());
        modelAndView.addObject("description", getRandomExcuse.execute());
        return modelAndView;
    }

    @GetMapping("/data/json")
    public ResponseEntity<Map<String, Object>> getDataPostJson() {
        Map<String, Object> itens = new HashMap<>();
        itens.put("pokemon", getPokemonByRandomNumber.execute());
        itens.put("description", getRandomExcuse.execute());
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * GET Data List
     */
    @GetMapping("/data/list")
    public ModelAndView getDataPostList() {
        List<DataPostResponse> dataPostResponseList = DataPostRequestMapper.toDataPostResponseList(findDataPost.execute());
        ModelAndView modelAndView = new ModelAndView("infoDataPostListView");
        modelAndView.addObject("pokemon", getPokemonByRandomNumber.execute());
        modelAndView.addObject("infoDataPostList", dataPostResponseList);
        return modelAndView;
    }

    @GetMapping("/data/list/json")
    public ResponseEntity<Map<String, Object>> getDataPostListJson() {
        List<DataPostResponse> dataPostResponseList = DataPostRequestMapper.toDataPostResponseList(findDataPost.execute());
        Map<String, Object> itens = new HashMap<>();
        itens.put("pokemon", getPokemonByRandomNumber.execute());
        itens.put("dataPostList", dataPostResponseList);
        itens.put("description", getRandomExcuse.execute());
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * GET Data by ID
     */
    @RequestMapping("/data/{id}")
    public ModelAndView getDataPostDetail(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("infoDataPostDetailView");
        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(findDataPost.execute(id));
        return mv.addObject("infoDataPost", dataPostResponse);
    }

    @GetMapping("/data/{id}/json")
    public ResponseEntity<Map<String, Object>> getDataPostDetailJson(@PathVariable("id") long id) {
        Map<String, Object> itens = new HashMap<>();
        DataPostResponse dataPostResponse = DataPostRequestMapper.toDataPostResponse(findDataPost.execute(id));
        itens.put("infoDataPost", dataPostResponse);
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * SAVE Data
     */
    @PostMapping("/new-data")
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
    @PostMapping("/delete")
    public String deleteDataPost(long id) {
        deleteDataPost.execute(id);
        return redirectIndexPage();
    }

    /**
     * Async
     */

    @GetMapping("/async/json")
    public ResponseEntity<List<String>> getDataPostAsyncJson(@RequestParam int to, @RequestParam int from) {
        return new ResponseEntity<>(getPokemonsByRangeNumber.execute(to, from), HttpStatus.OK);
    }

    /**
     * REDIRECT Route
     */
    @GetMapping("/")
    public String index() {
        return redirectIndexPage();
    }

    @GetMapping("/new-data")
    public String getDataPostForm() {
        return "dataPostForm";
    }

    private static String redirectIndexPage() {
        return "redirect:/gibao-app/data";
    }
}
