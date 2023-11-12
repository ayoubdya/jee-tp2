package dev.ayoub.backend.service;

import dev.ayoub.backend.model.Article;
import dev.ayoub.backend.model.Fragile;
import dev.ayoub.backend.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public void deleteArticle(Long id) {
       /* boolean exists = articleRepository.existsById(id);
        if (!exists) throw new IllegalStateException("Cant delete this article It doesn't exist");*/
        articleRepository.deleteById(id);
    }

    @Transactional
    public Article updateArticle(Long id, String name, Double prixHT) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalStateException("article doesnt exist"));
        if (name != null && !name.isEmpty()) article.setName(name);
        if (prixHT != null) article.setPrixHT(prixHT);
        return article;
    }

    @Transactional
    public Article updateFragile(Long id, String name, Double prixHT, String emballage) {
        System.out.println(1);
        Fragile fragile = (Fragile) articleRepository.findById(id).orElseThrow(() -> new IllegalStateException("fragile doesnt exist"));
        System.out.println(2);

        if (name != null && !name.isEmpty()) fragile.setName(name);
        if (emballage != null && !emballage.isEmpty()) fragile.setEmballage(emballage);
        if (prixHT != null) fragile.setPrixHT(prixHT);
        return fragile;
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article createFragile(Fragile fragile) {
        return articleRepository.save(fragile);
    }


}
