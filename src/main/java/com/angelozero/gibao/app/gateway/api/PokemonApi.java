package com.angelozero.gibao.app.gateway.api;

import com.angelozero.gibao.app.domain.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemon-api", url = "${pokemon.api.url}")
public interface PokemonApi {

    @GetMapping("{name}")
    Pokemon getImageByName(@PathVariable("name") String name);

    @GetMapping("{id}")
    Pokemon getImageByNumber(@PathVariable("id") Integer id);
}
