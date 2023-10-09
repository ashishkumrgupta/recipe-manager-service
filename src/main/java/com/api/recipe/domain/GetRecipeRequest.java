package com.api.recipe.domain;


import com.api.recipe.domain.validator.ValidGetRecipeRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@ValidGetRecipeRequest
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class GetRecipeRequest {
    @NotNull(message = "Empty RetrieveRecipeCategory provided")
    private RetrieveRecipeCategory category;
    private Boolean isVegetarian;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd‐MM‐yyyy HH:mm")
    private String creationDate;
    @Positive
    private Integer suitableFor;
    private List<String> ingredients;
    private String instructions;
}
