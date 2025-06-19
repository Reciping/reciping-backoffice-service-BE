package com.three.recipingbackofficeservicebe.recipe_management.controller;

import com.three.recipingbackofficeservicebe.global.logger.CustomLogger;
import com.three.recipingbackofficeservicebe.global.logger.LogType;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/recipes")
@RequiredArgsConstructor
public class AdminRecipeController {

    private final AdminRecipeService adminRecipeService;
    private static final Logger logger = LoggerFactory.getLogger(AdminRecipeController.class);
    private static final Logger errorLogger = LoggerFactory.getLogger("ERROR_LOGGER");

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<AdminRecipeDto>> getRecipeList(
            Pageable pageable,
            HttpServletRequest request) {
        try {
            Page<AdminRecipeDto> response = adminRecipeService.getRecipeList(pageable);

            // targetId는 목록 조회이므로 특정 ID가 없을 수 있습니다. "-" 또는 null 사용 가능.
            // payload에는 페이지 정보 등을 담을 수 있습니다.
            String payload = String.format("page:%d,size:%d", pageable.getPageNumber(), pageable.getPageSize());
            CustomLogger.track(
                    logger,
                    LogType.VIEW, // 레시피 목록 '조회'
                    "-",          // targetId: 특정 대상 없음
                    payload,      // payload: 페이지 정보
                    request
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            errorLogger.error("Error in getRecipeList(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRecipe(
            @PathVariable Long id,
            @RequestBody @Valid AdminRecipeRequestDto requestDto,
            HttpServletRequest request
    ) {
        try {
            adminRecipeService.updateRecipe(id, requestDto);

            // targetId는 업데이트 대상 레시피의 ID입니다.
            // payload에는 업데이트된 내용의 일부를 요약하거나, "-"로 비워둘 수 있습니다.
            // 여기서는 requestDto의 내용을 간단히 요약하거나, 단순히 업데이트 발생 사실만 기록할 수 있습니다.
            // 예시: requestDto.toString()은 너무 길 수 있으므로 필요한 정보만 선택하거나, "-" 사용
            String payload = "Recipe content updated"; // 또는 requestDto의 주요 필드 요약
            CustomLogger.track(
                    logger,
                    LogType.UPDATE,     // 레시피 '수정'
                    String.valueOf(id), // targetId: 수정된 레시피 ID
                    payload,            // payload: 수정 관련 정보
                    request
            );

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            errorLogger.error("Error in updateRecipe(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        try {
            adminRecipeService.deleteRecipe(id);

            // targetId는 삭제 대상 레시피의 ID입니다.
            // payload는 특별한 내용이 없다면 "-"로 설정할 수 있습니다.
            CustomLogger.track(
                    logger,
                    LogType.DELETE,     // 레시피 '삭제'
                    String.valueOf(id), // targetId: 삭제된 레시피 ID
                    "-",                // payload: 특별한 추가 정보 없음
                    request
            );

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            errorLogger.error("Error in deleteRecipe(): {}", e.getMessage(), e);
            throw e;
        }
    }
}
