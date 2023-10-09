package com.api.recipe.domain.validator;

import com.api.recipe.domain.GetRecipeRequest;
import com.api.recipe.domain.RetrieveRecipeCategory;
import com.api.recipe.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
public class GetRecipeRequestValidator implements ConstraintValidator<ValidGetRecipeRequest, GetRecipeRequest> {

    @Override
    public boolean isValid(GetRecipeRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request != null && validateType(request);
    }

    private boolean validateType(GetRecipeRequest request) {
        RetrieveRecipeCategory category = Optional.ofNullable(request.getCategory()).orElseThrow(() -> new BadRequestException("RetrieveRecipeCategory should not be empty"));
        switch (category) {
            case CREATION_DATETIME:
                log.debug("Switch: Getting recipes for CREATION_DATETIME");
                return null != request.getCreationDate();
            case IS_VEGETARIAN:
                log.debug("Switch: Getting recipes for IS_VEGETARIAN");
                return null != request.getIsVegetarian();
            case SUITABLE_FOR:
                log.debug("Switch: Getting recipes for SUITABLE_FOR");
                return null != request.getSuitableFor();
            case INGREDIENTS:
                log.debug("Switch: Getting recipes for INGREDIENTS");
                return null != request.getIngredients();
            case COOKING_INSTRUCTIONS:
                log.debug("Switch: Getting recipes for COOKING_INSTRUCTIONS");
                return null != request.getInstructions();
            default:
                throw new BadRequestException("RetrieveRecipeCategory should not be empty");
        }
    }
}