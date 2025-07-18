package com.three.recipingbackofficeservicebe.recipe_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.three.recipingbackofficeservicebe.recipe_management.dto.AdminRecipeDto;
import com.three.recipingbackofficeservicebe.recipe_management.service.AdminRecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminRecipeController.class)
class AdminRecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminRecipeService adminRecipeService;


    @Test
    @DisplayName("ADMIN 권한으로 레시피 목록 조회")
    void getRecipeListWithAdminAuth() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<AdminRecipeDto> dummyResponse = new PageImpl<>(Collections.emptyList(), pageable, 0);

        Mockito.when(adminRecipeService.getRecipeList(Mockito.any()))
                .thenReturn(dummyResponse);

        mockMvc.perform(get("/api/v1/admin/recipes")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}