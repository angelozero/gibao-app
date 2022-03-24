package com.angelozero.gibao.app.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.angelozero.gibao.app.config.exception.DataPostServiceException;
import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.usecase.datapost.ValidateDataPost;
import com.angelozero.gibao.app.util.MessagesUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MockitoSettings
class ValidateDataPostTest {

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.angelozero.gibao.template");
    }

    @InjectMocks
    private ValidateDataPost validateDataPost;


    @Test
    @DisplayName("Should set a secret user with success")
    void shouldSetSecretUserWithSuccess() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without SecretUser");
        assertNull(dataPostMock.getSecretUser());

        validateDataPost.execute(dataPostMock);

        assertNotNull(dataPostMock.getSecretUser());
    }

    @Test
    @DisplayName("Should thrown an exception when validate a data post without author")
    void shouldThrownAnExceptionToValidateADataPostWithoutAuthor() {

        DataPost dataPostMock = Fixture.from(DataPost.class).gimme("valid DataPost without Author");

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> validateDataPost.execute(dataPostMock));

        assertNotNull(exception);
        assertEquals(MessagesUtil.VALIDATE_DATA_POST_ERROR, exception.getError().getMessage());
        assertNotNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }

    @Test
    @DisplayName("Should thrown an exception when validate a null data post")
    void shouldThrownAnExceptionToValidateANullDataPost() {

        DataPostServiceException exception = assertThrows(DataPostServiceException.class, () -> validateDataPost.execute(null));

        assertNotNull(exception);
        assertEquals(MessagesUtil.VALIDATE_DATA_POST_ERROR, exception.getError().getMessage());
        assertNull(exception.getError().getIdentifier());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getError().getStatus());
    }
}
