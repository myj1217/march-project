package com.back.service;

import com.back.domain.ProductReply;
import com.back.dto.PageRequestDTO;
import com.back.dto.PageResponseDTO;
import com.back.dto.ProductReplyDTO;
import com.back.repository.ProductReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductReplyServiceImpl implements ProductReplyService{
    private final ProductReplyRepository productReplyRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(ProductReplyDTO productReplyDTO){
        ProductReply productReply = modelMapper.map(productReplyDTO, ProductReply.class);
        Long prno = productReplyRepository.save(productReply).getPrno();
        return  prno;
    }
    @Override
    public ProductReplyDTO read(Long prno){
        Optional<ProductReply> productReplyOptional = productReplyRepository.findById(prno);
        ProductReply productReply = productReplyOptional.orElseThrow();
        return modelMapper.map(productReply, ProductReplyDTO.class);
    }
    @Override
    public void modify(ProductReplyDTO productReplyDTO){
        Optional<ProductReply> replyOptional = productReplyRepository.findById(productReplyDTO.getPrno());
        ProductReply productReply = replyOptional.orElseThrow();
        productReply.changeText(productReplyDTO.getProductReplyText());// 댓글의 내용만 수정 가능
        productReplyRepository.save(productReply);
    }
    @Override
    public void remove(Long prno){
        productReplyRepository.deleteById(prno);
    }
    @Override
    public PageResponseDTO<ProductReplyDTO> getListOfProduct(Long pno, PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0?
                        0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("prno").ascending());
        Page<ProductReply> result = productReplyRepository.listOfProduct(pno, pageable);
        List<ProductReplyDTO> dtoList = result.getContent().stream()
                .map(productReply -> modelMapper.map(productReply, ProductReplyDTO.class))
                .collect(Collectors.toList());
        return PageResponseDTO.<ProductReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .totalCount((int)result.getTotalElements())
                .build();
    }
}