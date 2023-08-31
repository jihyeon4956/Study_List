package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.entity.UserRoleEnum;
import com.sparta.myselectshop.naver.dto.ItemDto;
import com.sparta.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto, User user) {
        // 받아온 Dto를 우리가 저장할 Entity형태로 변환해줘야 함
        Product product = productRepository.save(new Product(requestDto, user));
        return new ProductResponseDto(product);
    }

    @Transactional  // 변경감지가 필요한곳에는 꼭 사용하기
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        // 1. 가격 설정하기
        int requestedMyprice = requestDto.getMyprice(); // 단, 최저가는 100원 이상이어야 한다
        if (requestedMyprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 가격입니다. 최소 " + MIN_MY_PRICE + "원 이상으로 설정해 주세요.");
        }
        // 2.받아온 id에 해당하는 데이터가 있는지 확인
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품을 찾을 수 없습니다."));

        product.update(requestDto);

        return new ProductResponseDto(product);
    }

    @Transactional(readOnly = true)  // 성능 향상을 위해 옵션을 걸어줌
    // 지연로딩 기능을 사용할거임 (productfolder에   @OneToMany(mappedBy = "product")에서 디폴트값)
    // 개인계정 본인 상품만 조회
    public Page<ProductResponseDto> getProducts(User user, int page, int size, String sortBy, boolean isAsc) {
        // 오름차순인지 내림차순인지 정의
        Sort.Direction direction = isAsc? Sort.Direction.ASC: Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy); // 정렬기준: 방향, 항목
        // 페이징
        Pageable pageable = PageRequest.of(page, size, sort);

        // 관리자와 사용자 구분
        UserRoleEnum userRoleEnum = user.getRole(); // 현재 로그인해서 요청한 유저의 Role정보를 알 수 있음

        Page<Product> productList;  // page타입으로 감싸서 넘어옴

        if(userRoleEnum == UserRoleEnum.USER) {
            productList = productRepository.findAllByUser(user, pageable);
        } else {
            productList = productRepository.findAll(pageable);
        }

        return productList.map(ProductResponseDto::new);  // Page의 map 내장기능 활용
    }

//    // 관리자 계정 정체상품 조회
//    public List<ProductResponseDto> getAllProducts() {
//        List<Product> productList = productRepository.findAll();
//        List<ProductResponseDto> responseDtoList = new ArrayList<>(); // 반환할 객체 미리 생성
//
//        for (Product product : productList) {
//            responseDtoList.add(new ProductResponseDto(product));
//        }
//        return responseDtoList;
//    }

    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품은 존재하지 않습니다"));
        product.updateByItemDto(itemDto);

    }


}
