package com.angelozero.gibao.app.util;

public class MessageInfo {

    private MessageInfo() {
    }

    //DeleteDataPost
    public static final String DELETE_DATA_POST_FAIL = "Error to delete a data post %s";
    public static final String DELETE_DATA_POST_LOG_INFO_CHECK = "Checking if data post {} exists";
    public static final String DELETE_DATA_POST_LOG_INFO_DELETE = "Deleting data post {}";
    public static final String DELETE_DATA_POST_LOG_ERROR_DELETE = "Error to delete data post";

    //DeleteDataPostThread
    public static final String DELETE_DATA_POST_THREAD_INFO_VALIDATE = "[ THREAD ] - Validating data post to delete by thread";
    public static final String DELETE_DATA_POST_THREAD_INFO_DELETE = "[ THREAD ] - Deleting data post by thread";
    public static final String DELETE_DATA_POST_THREAD_INFO_START_THREAD = "[ THREAD ] - Starting thread with {} minutes";
    public static final String DELETE_DATA_POST_THREAD_ERROR_DELETE = "[ THREAD ] - Error to delete data post by thread";
    public static final String DELETE_DATA_POST_THREAD_ERROR_DELETE_INFO = "[ THREAD ] - Error to delete data post by thread %s";

    //FindDataPost
    public static final String FIND_DATA_POST_LIST_INFO = "Finding for a list of data post";
    public static final String FIND_DATA_POST_BY_ID_INFO = "Find a data post by id {}";
    public static final String FIND_DATA_POST_LIST_ERROR = "Error to find a list of data post";
    public static final String FIND_DATA_POST_LIST_ERROR_INFO = "Error to find a list of data post %s";
    public static final String FIND_DATA_POST_ID_ERROR = "Error to find a data post by id {}";
    public static final String FIND_DATA_POST_ID_ERROR_INFO = "Error to find a data post by id %s";

    //SaveDataPost
    public static final String SAVE_DATA_POST_INFO = "Saving post data";
    public static final String SAVE_DATA_POST_INFO_DELETE_IF_NOT_SECRET_USER = "Validating and deleting a post data if wasn't created by a secret user";
    public static final String SAVE_DATA_POST_ERROR = "Error to save post data";
    public static final String SAVE_DATA_POST_ERROR_INFO = "Error to save a post data %s";

    //GetPokemonByName
    public static final String GET_POKEMON_BY_NAME_INFO = "Calling PokeApi - by name {}";
    public static final String GET_POKEMON_BY_NAME_ERROR = "Error to call PokeApi";
    public static final String GET_POKEMON_BY_NAME_ERROR_INFO = "Error to get pokemon image %s";

    //GetPokemonByNumber
    public static final String GET_POKEMON_BY_NUMBER_INFO = "Calling PokeApi - by number {}";
    public static final String GET_POKEMON_BY_NUMBER_ERROR = "Error to call PokeApi";
    public static final String GET_POKEMON_BY_NUMBER_ERROR_INFO = "Error to get pokemon image %s";

    //ValidateDataPost
    public static final String VALIDATE_DATA_POST = "Validating a post data";
    public static final String VALIDADTE_DATA_POST_ERROR = "Error to validate data post - No Author was informed";

    //DataPostModelMapper
    public static final String DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_DATA_MODEL = "Error to convert data post to a data post model";
    public static final String DATA_POST_MODEL_MAPPER_ERROR_CONVERT_TO_POST_DATA = "Error to convert data post model to a data post";

    //DataPostRequestMapper
    public static final String DATA_POST_REQUEST_MAPPER_ERROR_NULL_AUTHOR = "Error to validate a Author";
    public static final String DATA_POST_REQUEST_MAPPER_ERROR_NULL_DATA_POST = "Error to convert a post data request to post data";
}
