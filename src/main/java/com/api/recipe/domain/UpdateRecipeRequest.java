package com.api.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeRequest {
    @NotNull(message = "Id is mandatory")
    private Long id;

    private Boolean isVegetarian;

    @Positive(message = "SuitableFor can only be a positive number")
    private Integer suitableFor;

    private List<String> ingredients;

    private String instructions;
}