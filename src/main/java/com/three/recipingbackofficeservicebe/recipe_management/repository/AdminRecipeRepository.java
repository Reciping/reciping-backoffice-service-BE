package com.three.recipingbackofficeservicebe.recipe_management.repository;

import com.three.recipingbackofficeservicebe.recipe_management.entity.AdminRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRecipeRepository extends JpaRepository<AdminRecipe, Long> {
}
