package com.example.Home.service;


import com.example.Home.dto.ArticleDTO;
import com.example.Home.entity.Article;
import com.example.Home.repository.ArticleRepo;
import com.example.Home.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    ModelMapper modelMapper;

    private final Path rootLocation = Paths.get("images");

    public String saveArticle(ArticleDTO articleDTO, MultipartFile file) {
        try {
            // Handle file upload
            if (file != null && !file.isEmpty()) {
                Files.createDirectories(rootLocation);
                Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                        .normalize().toAbsolutePath();
                file.transferTo(destinationFile);
                articleDTO.setImage(file.getOriginalFilename());
            }

            // Map DTO to entity
            Article article = modelMapper.map(articleDTO, Article.class);
            // Set postedDate
            article.setPostedDate(LocalDateTime.now());

            articleRepo.save(article);
            return VarList.RSP_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }


    public String updateArticle(ArticleDTO articleDTO, MultipartFile file) {
        if (articleRepo.existsById(articleDTO.getArticleID())) {
            Article existingArticle = articleRepo.findById(articleDTO.getArticleID()).orElse(null);

            if (existingArticle != null) {
                // Update fields
                existingArticle.setTopic(articleDTO.getTopic());
                existingArticle.setText(articleDTO.getText());

                // Handle file upload
                if (file != null && !file.isEmpty()) {
                    try {
                        Files.createDirectories(rootLocation);
                        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                                .normalize().toAbsolutePath();
                        file.transferTo(destinationFile);
                        existingArticle.setImage(file.getOriginalFilename());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return VarList.RSP_ERROR;
                    }
                } else {
                    existingArticle.setImage(articleDTO.getImage());
                }

                articleRepo.save(existingArticle);
                return VarList.RSP_SUCCESS;
            }
        }
        return VarList.RSP_NO_DATA_FOUND;
    }


    public List<ArticleDTO> getAllArticles() {
        List<Article> articleList = articleRepo.findAll();
        return modelMapper.map(articleList, new TypeToken<ArrayList<ArticleDTO>>(){}.getType());
    }

    public ArticleDTO searchArticle(int articleID) {
        if (articleRepo.existsById(articleID)) {
            Article article = articleRepo.findById(articleID).orElse(null);
            return modelMapper.map(article, ArticleDTO.class);
        } else {
            return null;
        }
    }

    public String deleteArticle(int articleID) {
        if (articleRepo.existsById(articleID)) {
            articleRepo.deleteById(articleID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
