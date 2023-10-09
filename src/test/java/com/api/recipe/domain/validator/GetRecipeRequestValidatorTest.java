package com.api.recipe.domain.validator;

import com.api.recipe.domain.GetRecipeRequest;
import com.api.recipe.domain.RetrieveRecipeCategory;
import com.api.recipe.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRecipeRequestValidatorTest {
    @InjectMocks
    private GetRecipeRequestValidator unitToTest;

    @Mock
    private GetRecipeRequest request;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    void testIsValidWhenRequestIsnull() {
        Assertions.assertThrows(BadRequestException.class, () -> unitToTest.isValid(request, constraintValidatorContext), "RetrieveRecipeCategory should not be empty");
    }

    @Test
    void testIsValidWhenCategoryAndValueIsCorrect() {
        when(request.getCategory()).thenReturn(RetrieveRecipeCategory.IS_VEGETARIAN);
        when(request.getIsVegetarian()).thenReturn(Boolean.TRUE);
        boolean result = unitToTest.isValid(request, constraintValidatorContext);
        assertTrue(result);
    }

    @Test
    void testIsValidWhenCategoryAndValueAreMismatch() {
        when(request.getCategory()).thenReturn(RetrieveRecipeCategory.COOKING_INSTRUCTIONS);
        boolean result = unitToTest.isValid(request, constraintValidatorContext);
        assertFalse(result);
    }
}