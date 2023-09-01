package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.*;
import com.sparta.myselectshop.naver.dto.ItemDto;
import com.sparta.myselectshop.repository.FolderRepository;
import com.sparta.myselectshop.repository.ProductFolderRepository;
import com.sparta.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FolderRepository folderRepository;
    private final ProductFolderRepository productFolderRepository;

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

    // 관심상품에 폴더 추가하는 기능
    public void addFolder(Long productId, Long folderId, User user) {
        // 1. productId를 받았으니까 Product 상품을 조회해보기
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NullPointerException("해당 상품이 존재하지 않습니다.")
        );
        // 2. Folder 조회하기
        Folder folder = folderRepository.findById(folderId).orElseThrow(
                () -> new NullPointerException("해당 폴더가 존재하지 않습니다.")
        );
        // 3. 받아온 productId와 folderId를 사용해서 해당하는 상품과 폴더가 데이터베이스에 잘 있는지 확인했고
        // 이제는 받아온 유저정보를 이용하여 로그인한 유저가 등록한 상품과 폴더가 맞는지 검증 필요
        if(!product.getUser().getId().equals(user.getId())
        || !folder.getUser().getId().equals(user.getId())){
            // 일치하지 않는걸 부정 -> true로 실행될 경우 오류인거임
            // "현재 로그인한 유저가 등록한 상품이나 폴더가 아니라면"
            throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다");
        }

        // 4. 등록된 폴더 중복여부 확인 -> 하나의 상품을 여러 폴더에 등록할 수 있지만 폴더하나에 같은 상품을 여러번 등록할 수는 없음
        // == 중복불가 (확인은 중간테이블인 ProductFolder)
        Optional<ProductFolder> overlapFolder = productFolderRepository.findByProductAndFolder(product, folder);
        // 찾은 값이 있는지 확인하기
        if (overlapFolder.isPresent()) { // 존재하는 경우 = 중복임
            throw new IllegalArgumentException("중복된 폴더입니다");
        }
        productFolderRepository.save(new ProductFolder(product, folder)); // 연관관계 설정
    }
}
