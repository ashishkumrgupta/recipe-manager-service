package com.api.recipe.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String msg) {
        super(msg);
    }
}
