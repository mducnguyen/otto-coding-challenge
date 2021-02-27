package de.duc.nguyen.occ.catalogfilter.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.models.ExceptionResponse;
import de.duc.nguyen.occ.catalogfilter.models.sort.SortInvalidPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import javax.ws.rs.WebApplicationException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ExceptionResponse jsonsProcessingExceptionHandler(Exception ex) {
        return toResponse(ex, HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(SortInvalidPropertyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse sortInvalidPropertyException(Exception ex) {
        return toResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ExceptionResponse restClientExceptionHandler(Exception ex) {
        return toResponse(ex, HttpStatus.FAILED_DEPENDENCY);
    }

    private ExceptionResponse toResponse(Exception exception, HttpStatus httpStatus) {
        int code = httpStatus.value();
        if (exception instanceof WebApplicationException) {
            code = ((WebApplicationException) exception).getResponse().getStatus();
        }

        return ExceptionResponse.builder()
                .code(code)
                .errorMessage(exception.getMessage())
                .error(httpStatus.getReasonPhrase())
                .build();
    }

}

