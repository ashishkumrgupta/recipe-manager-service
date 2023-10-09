package com.api.recipe.it;

import com.api.recipe.exception.RecipeNotFoundException;
import com.api.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeControllerTestIT {

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }


    @Test
    void givenAKnownRecipeIdInURIWhenCallingDeleteEndPointThenRecipeShouldBeDeleted() throws Exception {
        doNothing().when(recipeService).deleteRecipes(123456L);
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/recipes?id=123456L"))
                .andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void givenAUnknownRecipeIdInURIWhenCallingDeleteEndPointThenNotFoundException() throws Exception {
        doThrow(new RecipeNotFoundException("")).when(recipeService).deleteRecipes(123456L);
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/recipes?id=123456L"))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
