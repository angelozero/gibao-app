package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.config.error.Error;
import com.angelozero.gibao.app.config.exception.PokemonApiException;
import com.angelozero.gibao.app.gateway.api.PokemonApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class GetPokemonByName {

    public static final int FIRST_SEASON = 150;
    private final PokemonApi pokemonApi;

    public String execute() {
        String pokemonName = getRandomName();

        try {
            log.info("[ INFO ] - Calling PokeApi - by name {}", pokemonName);
            return pokemonApi.getImageByName(pokemonName).getSprites().getOther().getOfficialArtWork().getFrontDefault();

        } catch (Exception ex) {
            log.error("[ ERROR ] - Error to call PokeApi");
            throw new PokemonApiException(Error.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .identifier(pokemonName)
                    .message("Failed to get pokemon image")
                    .build(), ex);
        }
    }

    private static String getRandomName() {

        List<String> pokemonNames = Arrays.asList("bulbasaur",
                "ivysaur", "venusaur", "charmander", "charmeleon", "charizard", "squirtle", "wartortle",
                "blastoise", "caterpie", "metapod", "butterfree", "weedle", "kakuna", "beedrill", "pidgey",
                "pidgeotto", "pidgeot", "rattata", "raticate", "spearow", "fearow", "ekans", "arbok", "pikachu",
                "raichu", "sandshrew", "sandslash", "nidoran♀", "nidorina", "nidoqueen", "nidoran♂", "nidorino",
                "nidoking", "clefairy", "clefable", "vulpix", "ninetales", "jigglypuff", "wigglytuff", "zubat",
                "golbat", "oddish", "gloom", "vileplume", "paras", "parasect", "venonat", "venomoth", "diglett",
                "dugtrio", "meowth", "persian", "psyduck", "golduck", "mankey", "primeape", "growlithe", "arcanine",
                "poliwag", "poliwhirl", "poliwrath", "abra", "kadabra", "alakazam", "machop", "machoke", "machamp",
                "bellsprout", "weepinbell", "victreebel", "tentacool", "tentacruel", "geodude", "graveler", "golem",
                "ponyta", "rapidash", "slowpoke", "slowbro", "magnemite", "magneton", "farfetchd", "doduo", "dodrio",
                "seel", "dewgong", "grimer", "muk", "shellder", "cloyster", "gastly", "haunter", "gengar", "onix",
                "drowzee", "hypno", "krabby", "kingler", "voltorb", "electrode", "exeggcute", "exeggutor", "cubone",
                "marowak", "hitmonlee", "hitmonchan", "lickitung", "koffing", "weezing", "rhyhorn", "rhydon", "chansey",
                "tangela", "kangaskhan", "horsea", "seadra", "goldeen", "seaking", "staryu", "starmie", "mr-mime",
                "scyther", "jynx", "electabuzz", "magmar", "pinsir", "tauros", "magikarp", "gyarados", "lapras",
                "ditto", "eevee", "vaporeon", "jolteon", "flareon", "porygon", "omanyte", "omastar", "kabuto",
                "kabutops", "aerodactyl", "snorlax", "articuno ", "zapdos ", "moltres ",
                "dratini", "dragonair", "dragonite", "mewtwo ", "mew");

        return pokemonNames.get(new Random().nextInt(pokemonNames.size()));
    }
}
