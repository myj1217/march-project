package com.back.repository;

import com.back.domain.ComBoard;
import com.back.dto.ComBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComBoardRepository extends JpaRepository <ComBoard, Long> {
    @Query("SELECT cb FROM ComBoard cb WHERE cb.comBoardTitle LIKE %:titleKeyword% OR cb.comBoardContent LIKE %:contentKeyword%")
    Page<ComBoard> findByComBoardSearchAll(String titleKeyword, String contentKeyword, Pageable pageable);

    @Query("SELECT new com.back.dto.ComBoardDTO(cb.comBoardBno, cb.comBoardTitle, cb.comBoardContent, cb.comBoardWriter, cb.imagePath) FROM ComBoard cb WHERE cb.comBoardBno = :cbImage")
    ComBoardDTO findComBoardDTOWithImagePathById(Long cbImage);
}
