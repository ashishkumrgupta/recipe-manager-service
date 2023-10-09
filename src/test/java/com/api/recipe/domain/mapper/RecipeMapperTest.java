package com.api.recipe.domain.mapper;

import com.api.recipe.domain.UpdateRecipeRequest;
import com.api.recipe.entity.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeMapperTest {

    @InjectMocks
    private RecipeMapper unitToTest;

    @Mock
    private UpdateRecipeRequest request;


    @Test
    void testUpdateRecipeOnlySuitableFor() {
        when(request.getSuitableFor()).thenReturn(10);
        Recipe recipe = new Recipe();
        Recipe result = unitToTest.updateRecipe(recipe, request);
        assertEquals(10, result.getSuitableFor());
        assertNull(result.getCreationDate());
    }

    @Test
    void testUpdateRecipeOnlySuitableForAndIngredients() {
        when(request.getSuitableFor()).thenReturn(10);
        when(request.getIngredients()).thenReturn(asList("This", "That"));
        Recipe recipe = new Recipe();
        recipe.setIngredients(asList("Those", "their"));
        Recipe result = unitToTest.updateRecipe(recipe, request);
        assertEquals(10, result.getSuitableFor());
        assertEquals(asList("This", "That"), result.getIngredients());
    }
}