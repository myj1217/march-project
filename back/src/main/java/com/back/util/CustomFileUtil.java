package com.back.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {
    // 업로드 경로
    @Value("${com.back.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        File tempFolder = new File(uploadPath);

        // 업로드 경로에 폴더가 존재하지 않으면 생성
        if(tempFolder.exists() == false) {
            tempFolder.mkdir();
        }

        uploadPath = tempFolder.getAbsolutePath();

        log.info("-------------------------------------");
        log.info(uploadPath);
    }

    public List<String> saveFiles(List<MultipartFile> files)throws RuntimeException{
        if(files == null || files.size() == 0){
            return null; //List.of();
        }

        List<String> uploadNames = new ArrayList<>();

        for (MultipartFile multipartFile : files) {

            String savedName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
            Path savePath = Paths.get(uploadPath, savedName);

            try {
                Files.copy(multipartFile.getInputStream(), savePath);
                String contentType = multipartFile.getContentType();

                // 이미지 파일인 경우 썸네일 생성
                if(contentType != null && contentType.startsWith("image")){
                    Path thumbnailPath = Paths.get(uploadPath, "s_"+savedName);
                    Thumbnails.of(savePath.toFile())
                            .size(200, 200)
                            .toFile(thumbnailPath.toFile());
                }
                uploadNames.add(savedName);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }//end for
        return uploadNames;
    }

    // 응답 파일 데이터
    public ResponseEntity<Resource> getFile(String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        // 파일을 읽어들일 수 없는 경우 기본 이미지 반환
        if( !resource.isReadable()){
            resource = new FileSystemResource(uploadPath + File.separator + "error.jpg");
        }

        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    // 서버 내부에서 파일 삭제
    public void deleteFiles(List<String> fileNames){
        if(fileNames == null || fileNames.size() == 0){
            return;
        }

        fileNames.forEach(fileName ->{
            // 썸네일이 있는지 확인하고 삭제
            String thumbnailFileName = "s_" + fileName;
            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);

            try{
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            }catch(IOException e){
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}