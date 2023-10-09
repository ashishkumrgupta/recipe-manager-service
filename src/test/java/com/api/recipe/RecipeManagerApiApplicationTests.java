package com.api.recipe;

import com.api.recipe.controllers.RecipeController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecipeManagerApiApplicationTests {

    @Autowired
    private RecipeController unitToTest;

    @Test
    void contextLoads() {
        Assertions.assertThat(unitToTest).isNotNull();
    }
}
