package com.api.recipe.domain.mapper;

import com.api.recipe.domain.CreateRecipeRequest;
import com.api.recipe.domain.UpdateRecipeRequest;
import com.api.recipe.entity.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RecipeMapper {

    public Recipe updateRecipe(Recipe recipe, UpdateRecipeRequest request) {
        Optional<String> instructionsOptional = Optional.ofNullable(request.getInstructions());
        instructionsOptional.ifPresent(recipe::setInstructions);

        Optional<Boolean> isVegetarianOptional = Optional.ofNullable(request.getIsVegetarian());
        isVegetarianOptional.ifPresent(recipe::setIsVegetarian);

        Optional<Integer> suitableForOptional = Optional.ofNullable(request.getSuitableFor());
        suitableForOptional.ifPresent(recipe::setSuitableFor);

        Optional<List<String>> ingredientsOptional = Optional.ofNullable(request.getIngredients());
        ingredientsOptional.ifPresent(recipe::setIngredients);

        return recipe;
    }

    public Recipe mapRecipeRequest(CreateRecipeRequest request) {
        return new Recipe("fillhere", request.getIsVegetarian(), request.getSuitableFor(), request.getIngredients(), request.getInstructions());
    }
}
