package com.api.recipe.controllers;

import com.api.recipe.domain.*;
import com.api.recipe.service.RecipeService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {
    @MockBean
    private RecipeService recipeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void getRecipes() {
        GetRecipeRequest request = new GetRecipeRequest(RetrieveRecipeCategory.IS_VEGETARIAN, Boolean.TRUE, "02-05-2022 11:57", 3, asList("Onion", "tomato"), "instructions");
        Pageable pageable = Pageable.ofSize(10);
        Mockito.when(recipeService.getRecipes(request, pageable))
                .thenReturn(List.of(new RecipeResponse(123L, "ashish", "02-05-2022 11:57", Boolean.TRUE, 3, asList("Onion", "tomato"), "instructions")));
        given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .get("/recipes")
                .then()
                .status(HttpStatus.OK);
    }

    @Test
    void createRecipe() {
        List<String> ingredients = asList("Onion", "tomato");
        CreateRecipeRequest request = new CreateRecipeRequest(Boolean.TRUE, 3, ingredients, "instructions");
        Mockito.when(recipeService.createRecipe(request))
                .thenReturn(new RecipeResponse(123L, "ashish", "02-05-2022 11:57", Boolean.TRUE, 3, asList("Onion", "tomato"), "instructions"));
        given()
                .body(request)
                .contentType(ContentType.JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/recipes")
                .then()
                .status(HttpStatus.OK)
                .body("id", Matchers.equalTo(123))
                .body("instructions", Matchers.equalTo("instructions"));

    }

    @Test
    void createRecipeWithBadInput() {
        List<String> ingredients = asList("Onion", "tomato");
        CreateRecipeRequest request = new CreateRecipeRequest(Boolean.TRUE, 3, ingredients, "");
        Mockito.when(recipeService.createRecipe(request))
                .thenReturn(new RecipeResponse(123L, "ashish", "02-05-2022 11:57", Boolean.TRUE, 3, asList("Onion", "tomato"), "instructions"));
        given()
                .body(request)
                .contentType(ContentType.JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/recipes")
                .then()
                .status(HttpStatus.BAD_REQUEST)
                .body("errorMsg", Matchers.equalTo("INPUT_VALIDATION_FAILED"));
    }

    @Test
    void updateRecipe() {
        List<String> ingredients = asList("Onion", "tomato");
        UpdateRecipeRequest request = new UpdateRecipeRequest(1L, Boolean.TRUE, 3, ingredients, "instructions");
        Mockito.when(recipeService.updateRecipe(request))
                .thenReturn(new RecipeResponse(123L, "ashish", "02-05-2022 11:57", Boolean.TRUE, 3, asList("Onion", "tomato"), "instructions"));
        given()
                .body(request)
                .contentType(ContentType.JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/recipes")
                .then()
                .status(HttpStatus.OK);
    }

    @Test
    void deleteRecipe() {
        doNothing().when(recipeService).deleteRecipes(123456L);
        given()
                .param("id", 12346L)
                .when()
                .delete("/recipes")
                .then()
                .status(HttpStatus.ACCEPTED);
    }
}