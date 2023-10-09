package com.api.recipe.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeExceptionMsgConstants {
    public static final String INPUT_VALIDATION_FAILED = "INPUT_VALIDATION_FAILED";
    public static final String RECIPE_ID_NOT_FOUND = "INVALID_RECIPE_ID";
}
