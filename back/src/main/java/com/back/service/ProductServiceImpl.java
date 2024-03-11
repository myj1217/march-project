package com.back.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.domain.Product;
import com.back.domain.ProductImage;
import com.back.dto.PageRequestDTO;
import com.back.dto.PageResponseDTO;
import com.back.dto.ProductDTO;
import com.back.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("getList..............");

        // 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, // 페이지 시작 번호가 0부터 시작하므로 1을 빼줌
                pageRequestDTO.getSize(),                // 페이지 크기 설정
                Sort.by("pno").descending()); // pno 기준으로 내림차순 정렬

        // ProductRepository에서 상품 목록을 가져옴
        Page<Object[]>  result = productRepository.selectList(pageable);

        // 가져온 상품 목록을 ProductDTO 목록으로 매핑
        List<ProductDTO> dtoList = result.get().map(arr -> {

            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            // ProductDTO 생성
            ProductDTO productDTO = ProductDTO.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .delFlag(product.isDelFlag())	// 추가수정
                    .build();

            // 상품 이미지 파일명 설정
            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));

            return productDTO;
        }).collect(Collectors.toList());

        // 전체 상품 개수 가져오기
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<ProductDTO>withAll()
                .dtoList(dtoList)               // 상품 목록 정보
                .totalCount(totalCount)         // 전체 상품 개수
                .pageRequestDTO(pageRequestDTO) // 페이지 요청 정보
                .build();
    }

    @Override
    public Long register(ProductDTO productDTO) {
        Product product = dtoToEntity(productDTO);
        Product result = productRepository.save(product);

        return result.getPno();
    }

    private Product dtoToEntity(ProductDTO productDTO){
        Product product = Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .build();

        //업로드 처리가 끝난 파일들의 이름 리스트
        List<String> uploadFileNames = productDTO.getUploadFileNames();

        if(uploadFileNames == null){
            return product;
        }

        uploadFileNames.stream().forEach(uploadName -> {
            product.addImageString(uploadName);
        });

        return product;
    }

    @Override
    public ProductDTO get(Long pno) {
        java.util.Optional<Product> result = productRepository.selectOne(pno);
        Product product = result.orElseThrow();
        ProductDTO productDTO = entityToDTO(product);

        return productDTO;
    }

    private ProductDTO entityToDTO(Product product){
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .price(product.getPrice())
                .build();

        List<ProductImage> imageList = product.getImageList();

        if(imageList == null || imageList.size() == 0 ){
            return productDTO;
        }

        List<String> fileNameList = imageList.stream().map(productImage ->
                productImage.getFileName()).toList();
        productDTO.setUploadFileNames(fileNameList);

        return productDTO;
    }

    @Override
    public void modify(ProductDTO productDTO) {
        // 상품 번호로 해당 상품 조회
        Optional<Product> result = productRepository.findById(productDTO.getPno());
        // 상품이 없을 경우 예외를 던짐
        Product product = result.orElseThrow();

        // 상품 정보를 DTO에서 가져온 후 수정
        product.changeName(productDTO.getPname());  // 상품명 수정
        product.changeDesc(productDTO.getPdesc());  // 상품 설명 수정
        product.changePrice(productDTO.getPrice()); // 상품 가격 수정

        // 상품의 첨부 파일 목록 초기화
        product.clearList();

        // 업로드된 파일명 리스트를 가져와서 상품의 첨부 파일 목록에 추가
        List<String> uploadFileNames = productDTO.getUploadFileNames();

        if(uploadFileNames != null && uploadFileNames.size() > 0 ){
            uploadFileNames.stream().forEach(uploadName -> {
                product.addImageString(uploadName);
            });
        }

        // 상품을 저장하여 수정 완료
        productRepository.save(product);
    }

    @Override
    public void remove(Long pno) {
        productRepository.updateToDelete(pno, true);
    }

}