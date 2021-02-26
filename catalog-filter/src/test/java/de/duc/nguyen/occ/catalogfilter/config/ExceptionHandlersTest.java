package de.duc.nguyen.occ.catalogfilter.config;

import de.duc.nguyen.occ.catalogfilter.models.ExceptionResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;


public class ExceptionHandlersTest {

    ExceptionHandlers exceptionHandlers;

    @Before
    public void setUp() {
        exceptionHandlers = new ExceptionHandlers();
    }

    @Test
    public void whenJsonsProcessingExceptionHandler_thenReturnExceptionResponse(){

        String errorMessage = "This is a JsonPrecessingExceptionHandler";
        ExceptionResponse exceptionResponse = exceptionHandlers.jsonsProcessingExceptionHandler(new Exception(errorMessage));

        assertEquals(exceptionResponse.getErrorMessage(), errorMessage);
        assertEquals(exceptionResponse.getCode().intValue(), HttpStatus.FAILED_DEPENDENCY.value());
    }

    @Test
    public void whenRestClientException_thenReturnExceptionResponse(){

        String errorMessage = "This is a RestClientException";
        ExceptionResponse exceptionResponse = exceptionHandlers.restClientExceptionHandler(new Exception(errorMessage));

        assertEquals(exceptionResponse.getErrorMessage(), errorMessage);
        assertEquals(exceptionResponse.getCode().intValue(), HttpStatus.FAILED_DEPENDENCY.value());
    }
}