package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;


    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){ // @RequestBody: HTTP Body데이터 받아옴
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    // 관심상품 희망 최저가 등록
    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto){
        return productService.updateProduct(id, requestDto);
    }

    // 관심 상품 조회하기-로그인한 회원만
    // 페이징처리, 오름차순, 키워드별 정렬 등
    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(), page-1, size, sortBy, isAsc);
    }
//    비효율적
//    @GetMapping("/admin/products")
//    public List<ProductResponseDto> getAllProducts() {
//        return productService.getAllProducts();
//    }

    // 관심상품에 폴더 추가하기
    @PostMapping("/products/{productId}/folder")
    public void addFolder(
            @PathVariable Long productId,
            @RequestParam Long folderId, // Form 형식으로 폴더의 Id가 넘어옴
            @AuthenticationPrincipal UserDetailsImpl userDetails // 상품에 폴더를 추가하는데 그 해당상품과 해당 폴더가 현재 로그인한 유저의 상품과 폴더가 맞는지 확인이 필요하다
    ) {
        productService.addFolder(productId, folderId, userDetails.getUser());
    }

}
