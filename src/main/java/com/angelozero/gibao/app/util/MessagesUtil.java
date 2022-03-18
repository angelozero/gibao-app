package com.angelozero.gibao.app.util;

import org.apache.commons.lang3.StringUtils;

public class MessagesUtil {

    private MessagesUtil() {
    }

    //DeleteDataPost
    public static final String DELETE_DATA_POST_SUCCESS = "Data post id {} deleted with success";
    public static final String DELETE_DATA_POST_ERROR = "Error to delete a data post";

    //DeleteDataPostThread
    public static final String DELETE_DATA_POST_THREAD_INFO_START_THREAD = "[ THREAD ] - Starting thread with minutes {}";
    public static final String DELETE_DATA_POST_THREAD_SUCCESS = "[ THREAD ] - Success to delete Data Post by thread - id {}";
    public static final String DELETE_DATA_POST_THREAD_ERROR = "[ THREAD ] - Error to delete Data Post by thread";

    //FindDataPost
    public static final String FIND_DATA_POST_LIST_SUCCESS = "[ DB ] Success to find for a list of Data Post {}";
    public static final String FIND_DATA_POST_LIST_SUCCESS_BY_REDIS = "[ REDIS ] Success to find for a list of Data Post {}";
    public static final String NO_DATA_POST_LIST_FOUND_BY_REDIS = "[ REDIS ] No data was found for a list of Data Post";
    public static final String FIND_DATA_POST_LIST_ERROR_BY_REDIS = "[ REDIS ] Error to find for a list of Data Post {}";
    public static final String FIND_DATA_POST_LIST_ERROR = "Error to find a list of data post";
    public static final String FIND_DATA_POST_BY_ID_SUCCESS = "Find a Data Post by id {}";
    public static final String FIND_DATA_POST_ID_ERROR = "Error to find a Data Post by id";
    public static final String FIND_DATA_POST_BY_ID_NO_DATA_FOUND = " No Data Post found by id {}";

    //SaveDataPost
    public static final String SAVE_DATA_POST_SUCCESS = "Data Post saved with success {}";
    public static final String SAVE_DATA_POST_ERROR = "Error to save Data Post";

    //GetPokemonByName
    public static final String GET_POKEMON_BY_NAME_SUCCESS = "Success to get pokemon by name - {}";
    public static final String GET_POKEMON_BY_NAME_ERROR = "Error to get pokemon by name";

    //GetPokemonByNumber
    public static final String GET_POKEMON_BY_NUMBER_SUCCESS = "Success to get pokemon by number - {}";
    public static final String GET_POKEMON_BY_NUMBER_ERROR = "Error to get pokemon by number";

    //GetPokemonByRangeNumber
    public static final String GET_POKEMONS_BY_RANGER_NUMBER_SUCCESS = "Success to get pokemons by range number - {}";
    public static final String GET_POKEMONS_BY_RANGE_NUMBER_ERROR = "Error to get pokemon by range number";

    //RedisService
    public static final String REDIS_SERVICE_ERRO_TO_FIND_ALL = "Error to find all data in Redis";
    public static final String REDIS_SERVICE_SUCCESS_TO_FIND_ALL = "Success to find all data in Redis {}";
    public static final String REDIS_SERVICE_ERRO_TO_FIND = "Error to find a data in Redis";
    public static final String REDIS_SERVICE_SUCCESS_TO_FIND = "Success to find a data in Redis {}";
    public static final String REDIS_SERVICE_ERRO_TO_DELETE = "Error to delete data in Redis";
    public static final String REDIS_SERVICE_SUCCESS_TO_DELETE = "Success to delete data in Redis {}";
    public static final String REDIS_SERVICE_ERRO_TO_SAVE = "Error to save data in Redis";
    public static final String REDIS_SERVICE_SUCCESS_TO_SAVE = "Success to save data in Redis {} {} {}";

    //ValidateDataPost
    public static final String VALIDATE_DATA_POST_SUCCESS = "Data Post validated with success - is valid Author - {}";
    public static final String VALIDATE_DATA_POST_ERROR = "Error to validate Data Post - No Author was informed";

    //DataPostModelMapper
    public static final String DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_DATA_MODEL = "Error to convert Data Post to a Data Post model";
    public static final String DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_POST_DATA = "Error to convert Data Post model to a data post";

    //DataPostRequestMapper
    public static final String DATA_POST_REQUEST_MAPPER_ERROR_NULL_AUTHOR = "Error to validate a Author";
    public static final String DATA_POST_REQUEST_MAPPER_ERROR_NULL_DATA_POST = "Error to convert a post data request to post data";

    public static String join(String message1, String message2) {
        return StringUtils.joinWith(" - ", message1, message2);
    }
}
