package com.back.service;

import com.back.domain.ComBoard;
import com.back.dto.CbpRequestDTO;
import com.back.dto.CbpResponseDTO;
import com.back.dto.ComBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface ComBoardService {
    Long regComBoard(ComBoardDTO comBoardDTO);
//    ComBoardDTO getCbOne(Long comBoardBno);
    void modComBoard(ComBoardDTO comBoardDTO);
    void delComBoard(Long comBoardBno);
    CbpResponseDTO<ComBoardDTO> getCbList(CbpRequestDTO cbpRequestDTO);
    CbpResponseDTO<ComBoardDTO> searchCb(String query, CbpRequestDTO cbpRequestDTO);

    default String saveImage(MultipartFile file) throws IOException {
        // 이미지를 저장하는 코드를 여기에 구현
        // 파일을 저장하고 저장된 경로를 반환
        return "path/to/image"; // 임시로 경로를 반환하도록 설정
    }
    void saveComBoard(ComBoardDTO comBoardDTO, MultipartFile imageFile, String uploadDir) throws IOException;

    ComBoardDTO getComBoardWithImage(Long comBoardBno);

    default ComBoard dtoToEntity(ComBoardDTO comBoardDTO) {
        ComBoard comBoard = ComBoard.builder()
                .comBoardBno(comBoardDTO.getComBoardBno())
                .comBoardTitle(comBoardDTO.getComBoardTitle())
                .comBoardContent(comBoardDTO.getComBoardContent())
                .comBoardWriter(comBoardDTO.getComBoardWriter())
                .build();

        return comBoard;
    }

    default ComBoardDTO entityToDTO(ComBoard comBoard) {
        ComBoardDTO comBoardDTO = ComBoardDTO.builder()
                .comBoardBno(comBoard.getComBoardBno())
                .comBoardTitle(comBoard.getComBoardTitle())
                .comBoardContent(comBoard.getComBoardContent())
                .comBoardWriter(comBoard.getComBoardWriter())
                .cbRegDate(comBoard.getCbRegDate())
                .build();

        return comBoardDTO;
    }
}
