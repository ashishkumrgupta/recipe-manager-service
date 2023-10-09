package com.api.recipe.controllers;

import com.api.recipe.domain.*;
import com.api.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private final RecipeService recipeService;

    @Valid
    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getRecipes(@Valid @RequestBody GetRecipeRequest request,
                                                           @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(recipeService.getRecipes(request, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@Valid @NotNull(message = "Request body is mandatory") @RequestBody CreateRecipeRequest request) {
        return new ResponseEntity<>(recipeService.createRecipe(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RecipeResponse> updateRecipe(@Valid @NotNull @RequestBody UpdateRecipeRequest request) {
        return new ResponseEntity<>(recipeService.updateRecipe(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRecipe(@PathParam("id") Long id) {
        recipeService.deleteRecipes(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
