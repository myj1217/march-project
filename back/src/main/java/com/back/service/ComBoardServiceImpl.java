package com.back.service;

import com.back.domain.ComBoard;
import com.back.dto.CbpRequestDTO;
import com.back.dto.CbpResponseDTO;
import com.back.dto.ComBoardDTO;
import com.back.repository.ComBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2


public class ComBoardServiceImpl implements ComBoardService {

    private final ComBoardRepository comBoardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long regComBoard(ComBoardDTO comBoardDTO) {
        ComBoard comBoard = dtoToEntity(comBoardDTO);
        comBoardRepository.save(comBoard);
        return comBoard.getComBoardBno();
    }

    @Override
    public void modComBoard(ComBoardDTO comBoardDTO) {
        Optional<ComBoard> result = comBoardRepository.findById(comBoardDTO.getComBoardBno());
        ComBoard comBoard = result.orElseThrow();
        comBoard.cbChange(comBoardDTO.getComBoardTitle(), comBoardDTO.getComBoardContent());

        comBoardRepository.save(comBoard);
    }

    @Override
    public void delComBoard(Long comBoardBno) {
        comBoardRepository.deleteById(comBoardBno);
    }

    @Override
    public CbpResponseDTO<ComBoardDTO> getCbList(CbpRequestDTO cbpRequestDTO) {
        Pageable pageable;
        if (cbpRequestDTO != null) {
            pageable = cbpRequestDTO.getPageable();
        } else {
            pageable = PageRequest.of(0, 10); // 기본값으로 첫 페이지, 페이지 크기 10으로 설정
        }

        Page<ComBoard> comBoardPage = comBoardRepository.findAll(pageable);
        List<ComBoardDTO> dtoList = comBoardPage.getContent().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return new CbpResponseDTO<>(dtoList, comBoardPage.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public CbpResponseDTO<ComBoardDTO> searchCb(String query, CbpRequestDTO cbpRequestDTO) {
        Pageable pageable;
        if (cbpRequestDTO != null) {
            pageable = cbpRequestDTO.getPageable();
        } else {
            pageable = PageRequest.of(0, 10); // 기본 페이지 설정
        }

        Page<ComBoard> comBoardPage = comBoardRepository.findByComBoardSearchAll(query, query, pageable);
        List<ComBoardDTO> dtoList = comBoardPage.getContent().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return new CbpResponseDTO<>(dtoList, comBoardPage.getTotalElements(), pageable.getPageNumber(), pageable.getPageSize());
    }
    public String saveImage(MultipartFile file, String uploadDir) throws IOException {
        // 파일 이름 생성 (UUID 사용)
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 파일 저장 경로 설정
        Path filePath = Paths.get(uploadDir, fileName);

        // 파일 저장
        Files.write(filePath, file.getBytes());

        // 저장된 파일 경로 반환
        return filePath.toString();
    }

    public void saveComBoard(ComBoardDTO comBoardDTO, MultipartFile imageFile, String uploadDir) throws IOException {
        String imagePath = saveImage(imageFile, uploadDir); // 이미지 업로드 및 경로 저장
        comBoardDTO.setImagePath(imagePath); // ComBoardDTO에 이미지 경로 설정

        // ComBoardDTO를 ComBoard 엔티티로 변환
        ComBoard comBoard = modelMapper.map(comBoardDTO, ComBoard.class);
        comBoard.setCbRegDate(LocalDateTime.now()); // 등록 일시 설정

        // 게시글 저장
        comBoardRepository.save(comBoard);
    }

    @Override
    public ComBoardDTO getComBoardWithImage(Long comBoardBno) {
        ComBoard comBoard = comBoardRepository.findById(comBoardBno)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard not found with id: " + comBoardBno));

        return modelMapper.map(comBoard, ComBoardDTO.class);
    }

}
