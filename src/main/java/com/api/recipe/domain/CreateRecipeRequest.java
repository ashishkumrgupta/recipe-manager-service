package com.api.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequest {
    @NotNull(message = "Vegetarian indication is mandatory")
    private Boolean isVegetarian;

    @Positive(message = "SuitableFor can only be a positive number")
    private Integer suitableFor;

    @NotNull(message = "Ingredients are mandatory")
    private List<String> ingredients;

    @NotBlank(message = "Instructions are mandatory")
    private String instructions;
}
