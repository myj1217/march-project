package com.back.controller;

import com.back.dto.CbpRequestDTO;
import com.back.dto.CbpResponseDTO;
import com.back.dto.ComBoardDTO;
import com.back.service.ComBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/comboard", method = {RequestMethod.GET, RequestMethod.POST})

public class ComBoardController {

    private final ComBoardService comBoardService;

    @PostMapping("/register")
    public String regComBoard(@Valid ComBoardDTO comBoardDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register ComBoard error!";
        }
        Long comBoardBno = comBoardService.regComBoard(comBoardDTO);
        return "redirect:/list";
    }

    @GetMapping("/{comBoardBno}")
    public ResponseEntity<ComBoardDTO> getComBoard(@PathVariable Long comBoardBno) {
        ComBoardDTO comBoardDTO = comBoardService.getComBoardWithImage(comBoardBno);
        if (comBoardDTO != null) {
            return ResponseEntity.ok(comBoardDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/modify")
    public String modComBoard(@PathVariable Long comBoardBno, @Valid @RequestBody ComBoardDTO comBoardDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "modify ComBoard error!";
        }
        comBoardDTO.setComBoardBno(comBoardBno);
        comBoardService.modComBoard(comBoardDTO);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    public String delComBoard(@PathVariable Long comBoardBno) {
        comBoardService.delComBoard(comBoardBno);
        return "redirect:/comBoard/List";

    }

    @GetMapping
    public CbpResponseDTO<ComBoardDTO> getAllComBoards() {
        return comBoardService.getCbList(null);

    }

    @GetMapping("/search")
    public CbpResponseDTO<ComBoardDTO> searchCb(@RequestParam("query") String query, CbpRequestDTO cbpRequestDTO) {
        return comBoardService.searchCb(query, cbpRequestDTO);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            String imagePath = comBoardService.saveImage(file);
            if (imagePath != null && !imagePath.isEmpty()) {
                return new ResponseEntity<>("Image uploaded successfully. Image path: " + imagePath, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveComBoard(@ModelAttribute ComBoardDTO comBoardDTO,
                                               @RequestParam("file") MultipartFile imageFile,
                                               @RequestParam("uploadDir") String uploadDir) {
        // 게시글 및 이미지 저장
        try {
            comBoardService.saveComBoard(comBoardDTO, imageFile, uploadDir);
            return new ResponseEntity<>("ComBoard saved successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to save ComBoard", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
