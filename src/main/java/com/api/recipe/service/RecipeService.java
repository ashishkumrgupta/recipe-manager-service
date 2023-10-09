package com.api.recipe.service;

import com.api.recipe.domain.*;
import com.api.recipe.domain.mapper.RecipeMapper;
import com.api.recipe.domain.mapper.RecipeResponseMapper;
import com.api.recipe.entity.Recipe;
import com.api.recipe.entity.repository.RecipesRepository;
import com.api.recipe.exception.RecipeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.api.recipe.constants.RecipeExceptionMsgConstants.RECIPE_ID_NOT_FOUND;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipesRepository recipesRepository;
    private final RecipeMapper recipeMapper;
    private final RecipeResponseMapper recipeResponseMapper;

    public List<RecipeResponse> getRecipes(GetRecipeRequest request, Pageable pageable) {
        List<Recipe> allRecipes = getAllRecipesSwitch(request, pageable);
        List<RecipeResponse> responses = new ArrayList<>();
        allRecipes.forEach(recipe -> responses.add(recipeResponseMapper.map(recipe)));
        return responses;
    }

    private List<Recipe> getAllRecipesSwitch(GetRecipeRequest request, Pageable pageable) {
        List<Recipe> recipes;
        RetrieveRecipeCategory category = request.getCategory();
        log.debug("Getting recipes for category {}", category);
        switch (category) {
            case CREATION_DATETIME:
                recipes = recipesRepository.findAllByCreationDate(request.getCreationDate(), pageable);
                log.debug("Switch: Getting recipes for CREATION_DATETIME");
                break;
            case IS_VEGETARIAN:
                log.debug("Switch: Getting recipes for IS_VEGETARIAN");
                Boolean isVegetarian = request.getIsVegetarian();
                recipes = recipesRepository.findAllByIsVegetarian(isVegetarian, pageable);
                break;
            case SUITABLE_FOR:
                log.debug("Switch: Getting recipes for SUITABLE_FOR");
                Integer suitableFor = request.getSuitableFor();
                recipes = recipesRepository.findAllBySuitableFor(suitableFor, pageable);
                break;
            case INGREDIENTS:
                log.debug("Switch: Getting recipes for INGREDIENTS");
                List<String> ingredients = request.getIngredients();
                recipes = recipesRepository.findAllByIngredientsIn(ingredients, pageable);
                break;
            case COOKING_INSTRUCTIONS:
                log.debug("Switch: Getting recipes for COOKING_INSTRUCTIONS");
                String instructions = request.getInstructions();
                recipes = recipesRepository.findAllByInstructions(instructions, pageable);
                break;
            default:
                log.debug("Switch: Should be getting all recipes as no category provided");
                recipes = recipesRepository.findAll(pageable).toList();
        }
        return recipes.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Recipe::getId))),
                ArrayList::new));
    }


    public RecipeResponse createRecipe(CreateRecipeRequest request) {
        Recipe recipe = recipeMapper.mapRecipeRequest(request);
        Recipe createdRecipe = recipesRepository.save(recipe);
        return recipeResponseMapper.map(createdRecipe);
    }

    public RecipeResponse updateRecipe(UpdateRecipeRequest request) {
        Recipe recipe = recipesRepository.findById(request.getId()).orElseThrow(() -> new RecipeNotFoundException(RECIPE_ID_NOT_FOUND));
        recipe = recipeMapper.updateRecipe(recipe, request);
        Recipe updatedRecipe = recipesRepository.save(recipe);
        return recipeResponseMapper.map(updatedRecipe);
    }

    public void deleteRecipes(Long id) {
        Recipe recipe = recipesRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(RECIPE_ID_NOT_FOUND));
        recipesRepository.delete(recipe);
    }
}
