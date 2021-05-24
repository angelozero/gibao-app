package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateDataPostTest {

    @BeforeClass
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }


    @Test
    public void shouldSetSecretUserWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");
        assertNull(dataPostMock.getSecretUser());

        ValidateDataPost validateDataPost = new ValidateDataPost();

        validateDataPost.execute(dataPostMock);
        assertNotNull(dataPostMock.getSecretUser());
    }

    @Test
    public void shouldThrownAnExceptionToValidateADataPostWithoutAuthor() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without Author");
        ValidateDataPost validateDataPost = new ValidateDataPost();

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> validateDataPost.execute(dataPostMock));
        assertNotNull(exception);
        assertEquals(MessagesUtil.VALIDATE_DATA_POST_ERROR, exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }

    @Test
    public void shouldThrownAnExceptionToValidateANullDataPost() {

        ValidateDataPost validateDataPost = new ValidateDataPost();

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> validateDataPost.execute(null));
        assertNotNull(exception);
        assertEquals(MessagesUtil.VALIDATE_DATA_POST_ERROR, exception.getError().getMessage());
        assertNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }
}
