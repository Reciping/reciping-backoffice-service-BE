package com.three.recipingbackofficeservicebe.recipe_management.controller;

import com.three.recipingbackofficeservicebe.global.logger.CustomLogger;
import com.three.recipingbackofficeservicebe.global.logger.LogType;
import com.three.recipingbackofficeservicebe.global.security.UserDetailsImpl;
import com.three.recipingbackofficeservicebe.recipe_management.dto.AdminRecipeDto;
import com.three.recipingbackofficeservicebe.recipe_management.dto.AdminRecipeRequestDto;
import com.three.recipingbackofficeservicebe.recipe_management.service.AdminRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/recipes")
@RequiredArgsConstructor
public class AdminRecipeController {

    private final AdminRecipeService adminRecipeService;
    private static final Logger logger = LoggerFactory.getLogger(AdminRecipeController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<AdminRecipeDto>> getRecipeList(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            Pageable pageable,
            HttpServletRequest request) {
        Page<AdminRecipeDto> response = adminRecipeService.getRecipeList(pageable);

        CustomLogger.track(
                logger,
                LogType.VIEW,
                "/api/v1/admin/recipes",
                "GET",
                (userDetails != null) ? String.valueOf(userDetails.getUserId()) : null,
                null,
                null,
                null,
                request
        );

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRecipe(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id,
            @RequestBody @Valid AdminRecipeRequestDto requestDto,
            HttpServletRequest request
    ) {
        adminRecipeService.updateRecipe(id, requestDto);

        CustomLogger.track(
                logger,
                LogType.VIEW,
                "/api/v1/admin/recipes",
                "PUT",
                (userDetails != null) ? String.valueOf(userDetails.getUserId()) : null,
                null,
                String.valueOf(id),
                null,
                request
        );

        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        adminRecipeService.deleteRecipe(id);

        CustomLogger.track(
                logger,
                LogType.VIEW,
                "/api/v1/admin/recipes",
                "DELETE",
                (userDetails != null) ? String.valueOf(userDetails.getUserId()) : null,
                null,
                String.valueOf(id),
                null,
                request
        );

        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
