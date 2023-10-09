package com.api.recipe.entity;

import com.api.recipe.constants.RecipeConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd‐MM‐yyyy HH:mm")
    private String creationDate;
    private Boolean isVegetarian;
    private Integer suitableFor;
    @ElementCollection
    private List<String> ingredients;
    private String instructions;

    public Recipe(String createdBy, Boolean isVegetarian, Integer suitableFor, List<String> ingredients, String instructions) {
        this.creationDate = LocalDateTime.now().format(RecipeConstants.RECIPE_CREATION_DATE_FORMATTER);
        this.createdBy = createdBy;
        this.isVegetarian = isVegetarian;
        this.suitableFor = suitableFor;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}
