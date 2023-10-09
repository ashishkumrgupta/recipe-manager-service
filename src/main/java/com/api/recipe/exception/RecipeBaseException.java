package com.api.recipe.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecipeBaseException extends Exception {

    public RecipeBaseException(String msg) {
        super(msg);
    }
}
