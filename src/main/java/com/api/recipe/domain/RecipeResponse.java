package com.api.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RecipeResponse {
    private Long id;
    private String createdBy;
    private String creationDate;
    private Boolean isVegetarian;
    private Integer suitableFor;
    private List<String> ingredients;
    private String instructions;
}
