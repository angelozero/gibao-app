# gibao-app
![gibraltar](https://i.imgur.com/VpZHPVY.jpg)


## Postgres

#### 1. Enlisting the available databases
 - You can use the \l command to get a list of all available databases.

#### 2. Enlisting the available tables in the current database
- The next command does this for you 
- ```\dt ```

#### 3. Switching to another database
- The syntax for doing this: \c <database_name>. Suppose, you want to switch to a database named datacamp_tutorials you can do so like the following -
- ```\c datacamp_tutorials``` 


#### 4. Describing a particular table
 - The general syntax for doing is ```\d <table_name>```. Suppose, you are in the datacamp_tutorials database and you want to describe the table named countries. 
 - The command for this would be 
 - ```\d countries ```

 #### For more examples ---> [10 Command-line Utilities in PostgreSQL](https://www.datacamp.com/community/tutorials/10-command-line-utilities-postgresql?utm_source=adwords_ppc&utm_campaignid=1455363063&utm_adgroupid=65083631748&utm_device=c&utm_keyword=&utm_matchtype=b&utm_network=g&utm_adpostion=&utm_creative=332602034361&utm_targetid=aud-392016246653:dsa-429603003980&utm_loc_interest_ms=&utm_loc_physical_ms=1001773&gclid=Cj0KCQiAnKeCBhDPARIsAFDTLTIrlsOiY9m31jKm5SeMNVmaG4bMojBbJlhKQAVyLgdV5ueb_xYd3cgaAvm-EALw_wcB)

---

<<<<<<< HEAD

## Feing

#### 1. Feing Setup
- Add Feing Maven dependecy into your .pom file
```
        <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
            <version>3.0.2</version>
        </dependency>
```
- In your start application class add the anottation `@EnableFeignClients`
```
@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
- Create an interface that's will receive the PokeApi info, don't forge it to create a domain to.

Interface
```
@FeignClient(name = "pokemon-api", url = "https://pokeapi.co/api/v2/pokemon/")
public interface PokemonApi {

    @GetMapping("/{name}")
    Pokemon getImageByName(@PathVariable("name") String name);

    @GetMapping("/{id}")
    Pokemon getImageByNumber(@PathVariable("id") Integer id);
}
```

Domain ( take a look in class [Pokemon](https://github.com/angelozero/gibao-app/blob/master/src/main/java/com/angelozero/gibao/app/domain/Pokemon.java))
```

public class Pokemon {

    private Sprites sprites;

    public static class Sprites {

        private Other other;
    }

    public static class Other {

        @JsonProperty("official-artwork")
        private OfficialArtWork officialArtWork;
    }

    //some code here
```

#### 2. Call the API
- For use Feing you just need to call directly the api [Pokemon Service](https://github.com/angelozero/gibao-app/blob/master/src/main/java/com/angelozero/gibao/app/usecase/GetPokemon.java)

```
public class GetPokemon {

    private final PokemonApi pokemonApi;

    public String execute() {
        int pokemonNumber = getRandomNumber();

        try {
            return pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();

    //some code gere

```
#### 3. For more information !
- [Feign: Uma maneira elegante de criar clientes HTTP em Java](https://felixgilioli.medium.com/feign-uma-maneira-elegante-de-criar-clientes-http-em-java-c7c13c318cbe)

- [Pokemon API](https://pokeapi.co/) ![Pikachu](https://raw.githubusercontent.com/PokeAPI/media/master/logo/pokeapi_256.png)


## Add some gifs


 ![Octane](https://pa1.narvii.com/7219/3f6de78829849bb2c2425f764f9bde7ae52848e8r1-256-256_hq.gif)
 ![Octane](https://pa1.narvii.com/7219/04dd0e9ac40347ac391d9ba6323f6b822f182831r1-256-256_hq.gif)
 ![Wraith](https://64.media.tumblr.com/dfdf88c6295f4534e1b8e25b41b06b87/tumblr_przfnw4o471seajk2_400.png)
 ![Bloodhound](https://64.media.tumblr.com/2e397b84b9f4880b0f8c0954d04d3236/tumblr_przfnwsVdb1seajk2_400.png)
 ![Lifeline](https://64.media.tumblr.com/97e8abc9981caa9112a345f5c751db73/tumblr_przfnw2Nmo1seajk2_400.png)
 ![Bangalore](https://64.media.tumblr.com/cd94a364139ea43ba9fab89bb03bb6c3/tumblr_przfnwprJW1seajk2_400.png)
 
=======
 * Add some gifs and sprites

 ![Octane](https://pa1.narvii.com/7219/3f6de78829849bb2c2425f764f9bde7ae52848e8r1-256-256_hq.gif)
 ![Octane](https://pa1.narvii.com/7219/04dd0e9ac40347ac391d9ba6323f6b822f182831r1-256-256_hq.gif)
 
 ---

 ![Wraith](https://pbs.twimg.com/media/D6oRF_YWwAAi6OJ?format=png&name=360x360)
 ![Bangalore](https://pbs.twimg.com/media/D6oRF_aWAAAYcTo?format=png&name=360x360)
 ![Bloodhound](https://pbs.twimg.com/media/D6oRF_gW0AAIw4j?format=png&name=360x360)
 ![Lifeline](https://pbs.twimg.com/media/D6oRF_bWwAwqi4w?format=png&name=360x360)
>>>>>>> 63a2991... Adding some sprites in Readme
