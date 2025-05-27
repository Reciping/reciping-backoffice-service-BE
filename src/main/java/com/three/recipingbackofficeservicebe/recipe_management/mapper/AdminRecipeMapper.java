package com.three.recipingbackofficeservicebe.recipe_management.mapper;


import com.three.recipingbackofficeservicebe.recipe_management.dto.AdminRecipeDto;
import com.three.recipingbackofficeservicebe.recipe_management.entity.AdminRecipe;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        builder = @Builder(disableBuilder = true),
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminRecipeMapper {

    AdminRecipeDto toAdminDto(AdminRecipe recipe);

}
