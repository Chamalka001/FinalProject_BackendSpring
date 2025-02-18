package com.example.Home.controller;


import com.example.Home.dto.ArticleDTO;
import com.example.Home.dto.HouseDTO;
import com.example.Home.dto.ResponseDTO;
import com.example.Home.service.ArticleService;
import com.example.Home.util.VarList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ResponseDTO responseDTO;


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/saveArticle")
    public ResponseEntity<ResponseDTO> saveArticle(
            @RequestParam("file") MultipartFile file,
            @RequestParam("articleDTO") String articleDTOJson) {
        try {
            ArticleDTO articleDTO = new ObjectMapper().readValue(articleDTOJson, ArticleDTO.class);
            String res = articleService.saveArticle(articleDTO, file);

            if (VarList.RSP_SUCCESS.equals(res)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Article saved successfully.");
                responseDTO.setContent(articleDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (VarList.RSP_DUPLICATED.equals(res)) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Article already registered.");
                responseDTO.setContent(articleDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error saving article.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/updateArticle")
    public ResponseEntity<ResponseDTO> updateArticle(
            @RequestParam("articleDTO") String articleDTOJson,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            ArticleDTO articleDTO = new ObjectMapper().readValue(articleDTOJson, ArticleDTO.class);
            String result = articleService.updateArticle(articleDTO, file);

            if (VarList.RSP_SUCCESS.equals(result)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Article updated successfully.");
                responseDTO.setContent(articleDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else if (VarList.RSP_NO_DATA_FOUND.equals(result)) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No article found with the provided ID.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Failed to update article.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getAllArticles")
    public ResponseEntity<ResponseDTO> getAllArticles() {
        try {
            List<ArticleDTO> articleDTOList = articleService.getAllArticles();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Article retrieved successfully.");
            responseDTO.setContent(articleDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/searchArticle/{articleID}")
    public ResponseEntity<ResponseDTO> searchArticle(@PathVariable int articleID) {
        try {
            ArticleDTO articleDTO = articleService.searchArticle(articleID);

            if (articleDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Article found.");
                responseDTO.setContent(articleDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No article found with this ID.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/deleteArticle/{articleID}")
    public ResponseEntity<ResponseDTO> deleteArticle(@PathVariable int articleID) {
        try {
            String result = articleService.deleteArticle(articleID);

            if (VarList.RSP_SUCCESS.equals(result)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Article deleted successfully.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Article found with this ID.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
