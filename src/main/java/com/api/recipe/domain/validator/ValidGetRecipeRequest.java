package com.api.recipe.domain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {GetRecipeRequestValidator.class})
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ValidGetRecipeRequest {
    String message() default "Please fill the Object correctly";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
