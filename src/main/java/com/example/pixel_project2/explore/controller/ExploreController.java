package com.example.pixel_project2.explore.controller;

import com.example.pixel_project2.common.dto.ApiResponse;
import com.example.pixel_project2.config.jwt.AuthenticatedUser;
import com.example.pixel_project2.explore.dto.ExploreDesignerResponseDto;
import com.example.pixel_project2.explore.dto.ExplorePostResponseDto;
import com.example.pixel_project2.explore.service.ExploreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/explore")
@RequiredArgsConstructor
public class ExploreController {

    private final ExploreService exploreService;

    @GetMapping
    public ApiResponse<List<ExplorePostResponseDto>> getExploreFeeds(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String keyword) {

        List<ExplorePostResponseDto> response =
                exploreService.getExploreFeeds(category, page, size, keyword, currentUser.id());

        return ApiResponse.ok("?먯깋 ?쇰뱶 議고쉶媛 ?꾨즺?섏뿀?듬땲??", response);
    }

    @GetMapping("/designers")
    public ApiResponse<List<ExploreDesignerResponseDto>> getExploreDesigners(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "recommended") String sort) {

        List<ExploreDesignerResponseDto> response =
                exploreService.getExploreDesigners(keyword, page, size, sort);

        return ApiResponse.ok("?붿옄?대꼫 紐⑸줉 議고쉶媛 ?꾨즺?섏뿀?듬땲??", response);
    }
}
