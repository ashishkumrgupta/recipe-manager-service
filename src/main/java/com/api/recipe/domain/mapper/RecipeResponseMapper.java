package com.api.recipe.domain.mapper;

import com.api.recipe.domain.RecipeResponse;
import com.api.recipe.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeResponseMapper {
    public RecipeResponse map(Recipe recipe) {
        
        return RecipeResponse.builder().createdBy(recipe.getCreatedBy())
                .creationDate(recipe.getCreationDate())
                .id(recipe.getId())
                .ingredients(recipe.getIngredients())
                .instructions(recipe.getInstructions())
                .isVegetarian(recipe.getIsVegetarian())
                .suitableFor(recipe.getSuitableFor())
                .build();
    }
}
