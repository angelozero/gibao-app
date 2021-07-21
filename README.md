# gibao-app
![gibraltar](https://i.imgur.com/VpZHPVY.jpg)


## 1 - Postgres

### Enlisting the available databases
 - You can use the \l command to get a list of all available databases.

### Enlisting the available tables in the current database
- The next command does this for you 
- ```\dt ```

### Switching to another database
- The syntax for doing this: \c <database_name>. Suppose, you want to switch to a database named datacamp_tutorials you can do so like the following -
- ```\c datacamp_tutorials``` 


### Describing a particular table
 - The general syntax for doing is ```\d <table_name>```. Suppose, you are in the datacamp_tutorials database and you want to describe the table named countries. 
 - The command for this would be 
 - ```\d countries ```

#### For more examples ---> [10 Command-line Utilities in PostgreSQL](https://www.datacamp.com/community/tutorials/10-command-line-utilities-postgresql?utm_source=adwords_ppc&utm_campaignid=1455363063&utm_adgroupid=65083631748&utm_device=c&utm_keyword=&utm_matchtype=b&utm_network=g&utm_adpostion=&utm_creative=332602034361&utm_targetid=aud-392016246653:dsa-429603003980&utm_loc_interest_ms=&utm_loc_physical_ms=1001773&gclid=Cj0KCQiAnKeCBhDPARIsAFDTLTIrlsOiY9m31jKm5SeMNVmaG4bMojBbJlhKQAVyLgdV5ueb_xYd3cgaAvm-EALw_wcB)

---

## 2 - Feing

### Feing Setup
- Add Feing Maven dependecy into your .pom file
```xml
 <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign -->
 <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-openfeign</artifactId>
     <version>3.0.2</version>
 </dependency>
```
- In your start application class add the anottation `@EnableFeignClients`
```java
@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
- Create an interface that's will receive the PokeApi info, don't forge it to create a domain to.

```java
@FeignClient(name = "pokemon-api", url = "https://pokeapi.co/api/v2/pokemon/")
public interface PokemonApi {

    @GetMapping("/{name}")
    Pokemon getImageByName(@PathVariable("name") String name);

    @GetMapping("/{id}")
    Pokemon getImageByNumber(@PathVariable("id") Integer id);
}
```

 - Domain ( take a look in class [Pokemon.java](https://github.com/angelozero/gibao-app/blob/master/src/main/java/com/angelozero/gibao/app/domain/Pokemon.java) )
```java
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

### Call the API
- For use Feing you just need to call directly the [Poke Api](https://github.com/angelozero/gibao-app/blob/master/src/main/java/com/angelozero/gibao/app/usecase/GetPokemon.java)

```java
public class GetPokemon {

    private final PokemonApi pokemonApi;

    public String execute() {
        int pokemonNumber = getRandomNumber();

        try {
            return pokemonApi.getImageByNumber(pokemonNumber).getSprites().getOther().getOfficialArtWork().getFrontDefault();
    //some code gere
```
### For more information !
- [Feign: Uma maneira elegante de criar clientes HTTP em Java](https://felixgilioli.medium.com/feign-uma-maneira-elegante-de-criar-clientes-http-em-java-c7c13c318cbe)

- [Pokemon API](https://pokeapi.co/) 
  
![Pikachu](https://raw.githubusercontent.com/PokeAPI/media/master/logo/pokeapi_256.png)

--- 

## 3 - Docker and Redis

### Docker
Instal Docker
- https://docs.docker.com/docker-for-mac/install/

Test if ok type in terminal
- `docker run hello-world`

Output must be:

```shell
  Hello from Docker!
  This message shows that your installation appears to be working correctly.

  To generate this message, Docker took the following steps:
  1. The Docker client contacted the Docker daemon.
  2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
      (amd64)
  3. The Docker daemon created a new container from that image which runs the
      executable that produces the output you are currently reading.
  4. The Docker daemon streamed that output to the Docker client, which sent it
      to your terminal.

  To try something more ambitious, you can run an Ubuntu container with:
  $ docker run -it ubuntu bash

  Share images, automate workflows, and more with a free Docker ID:
  https://hub.docker.com/

  For more examples and ideas, visit:
  https://docs.docker.com/get-started/
```

Now let's go! First type this in terminal...

```shell
  docker run --name redis-data-base -p 6379:6379 redis
```

Where
- `--name redis-data-base` ---- is the name of your docker container ( "redis-data-base" is Redis Data Base for example )
- `-p 6379:6379` -- is the port where's gonna run the container
- `redis` --------- is the name of the Redis Image

Going inside the Redis container using the CLI ( Command Line Interface )
  - `docker exec -it redis-data-base redis-cli`

Set a key with value
  - `set my-name "angelo zero"`

Get the key value
  - `get my-name` must input `"angelo zero"`

Set a key with expiration time ( 10 seconds )
  - `set name-and-temp "angelo zero 10 seconds" EX 10`

Before 10 second if you type get nametemp you should receive the value test-time
  - `get name-and-temp` must input ( before 10 seconds ) `"angelo zero 10 seconds"`
  - `get name-and-temp` must input ( after 10 seconds ) `(nil)`

Check if a key exists
  - `exists my-name` - the output must be
    - if `(integer) 1` this mean `true`
    - else `(integer) 0` this mean `false`

Delete a key
  - `del my-name` must input `(integer) 1`

---
---

### Redis

Going inside the Redis container using the CLI ( Command Line Interface on Docker )
- `docker exec -it redis-data-base redis-cli`

Set a key with value
- `set my-name "angelo zero"`

Get the key value
- `get my-name` must input `"angelo zero"`

Set a key with expiration time ( 10 seconds )
- `set name-and-temp "angelo zero 10 seconds" EX 10`

Get the key value with expiration time
- `get name-and-temp` must input ( before 10 seconds ) `"angelo zero 10 seconds"`
- `get name-and-temp` must input ( after 10 seconds ) `(nil)`

Check if a key exists
- `exists my-name` - the output must be
    - if `(integer) 1` this mean `true`
    - else `(integer) 0` this mean `false`

Delete a key
- `del my-name` must input `(integer) 1`

Get all Keys
- `KEYS *`

Cleaning all data from Redis
- `FLUSHALL`

---
---

### Redis instaled by brew

Stop Redis Service ( With Redis instaled by brew )
- `brew services stop redis`

Start Redis Service ( With Redis instaled by brew )
- `brew services start redis`

Restart Redis Service ( With Redis instaled by brew )
- `brew services restart redis`

---
---

### Backend

The configuration class is [RedisConfig.class](https://github.com/angelozero/gibao-app/blob/master/src/main/java/com/angelozero/gibao/app/config/RedisConfig.java)

```java
@Configuration
@EnableRedisRepositories
public class RedisConfig {

  @Bean
  public JedisConnectionFactory connectionFactory() {
      RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
      configuration.setHostName("localhost");
      configuration.setPort(6379);
      return new JedisConnectionFactory(configuration);
  }
```

How to use - [FindDataPost.class](https://github.com/angelozero/gibao-app/blob/master/src/main/java/com/angelozero/gibao/app/usecase/FindDataPost.java)
```java
List<Object> redisCache = redisService.findAll(RedisInfo.HASH_KEY_DATA_POST);

  if (!redisCache.isEmpty()) {
      ObjectMapper objMapper = new ObjectMapper();
      List<DataPost> dataPostRedisCacheList = objMapper.readValue(redisCache.get(0).toString(), objMapper.getTypeFactory().constructParametricType(List.class, DataPost.class));
  
      log.info(MessagesUtil.FIND_DATA_POST_LIST_SUCCESS_BY_REDIS, dataPostRedisCacheList);
      return dataPostRedisCacheList;
  }
```

Maven dependency - [pom.xml](https://github.com/angelozero/gibao-app/blob/master/pom.xml)

```xml
<!-- Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>

<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.3.0</version>
</dependency>
```

### For more information
- [Introduction to Spring Data Redis](https://www.baeldung.com/spring-data-redis-tutorial)
- [Spring Boot Cache Fácil e Rápido! Com Redis!](https://www.youtube.com/watch?v=k58Dk5wXdvc)
- [Spring Boot | Spring Data Redis as Cache | @Cacheable | @CacheEvict | @CachePut | JavaTechie](https://www.youtube.com/watch?v=vpe4aDu5ixI)
- [Spring Boot | Spring Data Redis | Database | CRUD Example | JavaTechie](https://www.youtube.com/watch?v=oRGqCz8OLcM)


---

## Unit and Integration Tests

### Fixture
 - Falar sobre fixture

 - WIremock com Feing
  - https://www.programmersought.com/article/5355949210/

- Stubbing com Wiremock
  - Match URL 
    - http://one.wiremock.org/stubbing.html

- Explicar com codigo da classe SaveDataPostIntegrationTest


- Problemas que tive 
  - https://www.youtube.com/watch?v=pNg72FknNco -  JUNIT4
  - Erro - {"message":"No such image: bsideup/moby-ryuk:0.2.2"}
    - https://github.com/testcontainers/testcontainers-java/issues/3574 - 89Items
    - java.lang.NoClassDefFoundError: org/testcontainers/containers/wait/LogMessageWaitStrategy 
        - ```xml
                <dependency>
                <groupId>org.testcontainers</groupId>
                <artifactId>testcontainers</artifactId>
                <version>1.10.1</version>
            </dependency>
          ```
    - [main] DEBUG org.testcontainers.utility.TestcontainersConfiguration - Testcontainers configuration overrides will be loaded from file .../.testcontainers.properties
        - https://www.testcontainers.org/features/configuration/

### Testcontainers
![Testcontainer](https://d33wubrfki0l68.cloudfront.net/a661dbbe55be3e9cb77889f24835a44c6daf53c2/ce0aa/logo.png)
- Integration tests using [Testcontainers](https://www.testcontainers.org/)

### Wiremock
![How it works](https://image.slidesharecdn.com/resiliencetestingwithwiremock-160322164036/95/resilience-testing-with-wiremock-and-spock-9-638.jpg)
- [Wiremock](http://wiremock.org/)
- [Introducion to Wiremock](https://www.baeldung.com/introduction-to-wiremock)

---
## First BETA 0.0.1 preview!
![Excuse](https://s3.gifyu.com/images/ezgif.com-gif-makerad94f6de2a1f6698.gif)

---

*"Wanna fly, compadre? Let's fly."* - **Octane**
 
 ![Octane](https://pa1.narvii.com/7219/04dd0e9ac40347ac391d9ba6323f6b822f182831r1-256-256_hq.gif)

*"I’m not afraid-- I’ll never be."* - **Wraith**

![Wraith](https://i.pinimg.com/originals/97/9a/6a/979a6af7254269512e331b7f0abc1256.gif)

[comment]: <> ( ![Wraith]&#40;https://64.media.tumblr.com/dfdf88c6295f4534e1b8e25b41b06b87/tumblr_przfnw4o471seajk2_400.png&#41;)

[comment]: <> ( ![Bloodhound]&#40;https://64.media.tumblr.com/2e397b84b9f4880b0f8c0954d04d3236/tumblr_przfnwsVdb1seajk2_400.png&#41;)

[comment]: <> ( ![Lifeline]&#40;https://64.media.tumblr.com/97e8abc9981caa9112a345f5c751db73/tumblr_przfnw2Nmo1seajk2_400.png&#41;)

[comment]: <> ( ![Bangalore]&#40;https://64.media.tumblr.com/cd94a364139ea43ba9fab89bb03bb6c3/tumblr_przfnwprJW1seajk2_400.png&#41;)



 
---
