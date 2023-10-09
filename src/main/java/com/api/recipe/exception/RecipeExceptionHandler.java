package com.api.recipe.exception;

import com.api.recipe.constants.RecipeExceptionMsgConstants;
import com.api.recipe.domain.RecipeExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RecipeExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        RecipeExceptionResponse response = new RecipeExceptionResponse(RecipeExceptionMsgConstants.INPUT_VALIDATION_FAILED, HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecipeBaseException.class)
    protected ResponseEntity<RecipeExceptionResponse> handleRecipeBaseException(RuntimeException ex, WebRequest request) {
        RecipeExceptionResponse response = new RecipeExceptionResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RecipeNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        log.info("Handling RecipeNotFoundException");
        RecipeExceptionResponse response = new RecipeExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        log.info("Handling BadRequestException");
        ex.printStackTrace();
        RecipeExceptionResponse response = new RecipeExceptionResponse(ex.getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
