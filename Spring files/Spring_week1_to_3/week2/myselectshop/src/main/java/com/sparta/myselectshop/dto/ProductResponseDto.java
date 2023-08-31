package com.sparta.myselectshop.dto;

import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.entity.ProductFolder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String link;
    private String image;
    private int lprice;
    private int myprice;

    // 폴더의 정보도 함꼐 반환해줌
    private List<FolderResponseDto> productFolderList = new ArrayList<>();


    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();

        for (ProductFolder productFolder : product.getProductFolderList()) {
            productFolderList.add(new FolderResponseDto(productFolder.getFolder()));
            // produck 폴더에 만든 productFolderList를 가지고 옴
            // List<ProductFolder>에  있는 Folder 정보를 가지고 옴
            // 내가 원하는건 Folder 정보니까 그걸 가져옴, 그걸 FolderResponseDto로 변환하면서 리스트에 집어넣어줌
            // 그럼 결국 productFolderList에는 folder 정보가 담기게 됨
        }
    }
}