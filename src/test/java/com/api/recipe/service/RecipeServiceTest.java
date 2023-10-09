package com.api.recipe.service;

import com.api.recipe.domain.GetRecipeRequest;
import com.api.recipe.domain.RecipeResponse;
import com.api.recipe.domain.RetrieveRecipeCategory;
import com.api.recipe.domain.mapper.RecipeResponseMapper;
import com.api.recipe.entity.Recipe;
import com.api.recipe.entity.repository.RecipesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @InjectMocks
    private RecipeService unitToTest;

    @Mock
    private GetRecipeRequest request;

    @Mock
    private RecipesRepository recipesRepository;
    @Mock
    private Pageable pageable;

    @Mock
    private Recipe recipe1;
    @Mock
    private Recipe recipe2;
    @Mock
    private RecipeResponse recipeResponse1;
    @Mock
    private RecipeResponse recipeResponse2;
    @Mock
    private RecipeResponseMapper recipeResponseMapper;

    @Test
    void testGetRecipesEmpty() {
        when(request.getCategory()).thenReturn(RetrieveRecipeCategory.IS_VEGETARIAN);
        when(request.getIsVegetarian()).thenReturn(Boolean.TRUE);
        when(recipesRepository.findAllByIsVegetarian(Boolean.TRUE, pageable)).thenReturn(new ArrayList<>());
        List<RecipeResponse> result = unitToTest.getRecipes(request, pageable);
        assertEquals(0, result.size());
    }

    @Test
    void testGetRecipes() {
        when(recipe1.getId()).thenReturn(1L);
        when(recipe2.getId()).thenReturn(2L);
        when(request.getCategory()).thenReturn(RetrieveRecipeCategory.SUITABLE_FOR);
        when(request.getSuitableFor()).thenReturn(3);
        when(recipesRepository.findAllBySuitableFor(3, pageable)).thenReturn(asList(recipe1, recipe2));
        when(recipeResponseMapper.map(recipe1)).thenReturn(recipeResponse1);
        when(recipeResponseMapper.map(recipe2)).thenReturn(recipeResponse2);
        List<RecipeResponse> result = unitToTest.getRecipes(request, pageable);
        assertEquals(2, result.size());
    }

    @Test
    void testGetRecipesRemoveDuplicate() {
        when(recipe1.getId()).thenReturn(1L);
        when(recipe2.getId()).thenReturn(1L);
        when(request.getCategory()).thenReturn(RetrieveRecipeCategory.SUITABLE_FOR);
        when(request.getSuitableFor()).thenReturn(3);
        when(recipesRepository.findAllBySuitableFor(3, pageable)).thenReturn(asList(recipe1, recipe2));
        when(recipeResponseMapper.map(recipe1)).thenReturn(recipeResponse1);
        List<RecipeResponse> result = unitToTest.getRecipes(request, pageable);
        assertEquals(1, result.size());
        verifyNoMoreInteractions(recipeResponseMapper);
    }

}