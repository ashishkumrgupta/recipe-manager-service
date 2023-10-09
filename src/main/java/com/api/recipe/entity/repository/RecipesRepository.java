package com.api.recipe.entity.repository;

import com.api.recipe.entity.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByIsVegetarian(Boolean value, Pageable pageable);

    List<Recipe> findAllByCreationDate(String value, Pageable pageable);

    List<Recipe> findAllBySuitableFor(Integer value, Pageable pageable);

    List<Recipe> findAllByInstructions(String value, Pageable pageable);

    List<Recipe> findAllByIngredientsIn(List<String> value, Pageable pageable);
}
