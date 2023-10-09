package com.api.recipe.domain;

import com.api.recipe.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Getter
@AllArgsConstructor
public enum RetrieveRecipeCategory {
    CREATION_DATETIME("CREATION_DATETIME"),
    IS_VEGETARIAN("IS_VEGETARIAN"),
    SUITABLE_FOR("SUITABLE_FOR"),
    INGREDIENTS("INGREDIENTS"),
    COOKING_INSTRUCTIONS("COOKING_INSTRUCTIONS");

    public final String value;

    public static RetrieveRecipeCategory getCategoryType(String categoryValue) {
        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(categoryValue))
                .findFirst().orElse(null);
    }

    public static RetrieveRecipeCategory fromString(String value) {
        String toUpper = value.toUpperCase();
        try {
            return valueOf(toUpper);
        } catch (IllegalArgumentException e) {
            log.info("Error while getting RetrieveRecipeCategory");
            throw new BadRequestException("Invalid RetrieveRecipeCategory provided");
        }
    }
}
