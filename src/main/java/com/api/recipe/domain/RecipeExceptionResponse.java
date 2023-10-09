package com.api.recipe.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeExceptionResponse {
    private String errorMsg;
    private HttpStatus httpStatus;
    private LocalDateTime exceptionTime;
    private Map<String, String> validationErrors;


    public RecipeExceptionResponse(String errorMsg, HttpStatus httpStatus) {
        this(errorMsg, httpStatus, null);
    }

    public RecipeExceptionResponse(String errorMsg, HttpStatus httpStatus, Map<String, String> validationErrors) {
        super();
        this.errorMsg = errorMsg;
        this.httpStatus = httpStatus;
        this.exceptionTime = LocalDateTime.now();
        this.validationErrors = validationErrors;
    }
}
