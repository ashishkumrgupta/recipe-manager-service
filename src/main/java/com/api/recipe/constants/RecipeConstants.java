package com.api.recipe.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeConstants {
    public static final DateTimeFormatter RECIPE_CREATION_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
}
