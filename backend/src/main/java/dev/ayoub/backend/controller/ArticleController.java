package dev.ayoub.backend.controller;

import dev.ayoub.backend.model.Article;
import dev.ayoub.backend.model.Fragile;
import dev.ayoub.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping()
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping(path = "{articleId}")
    public Optional<Article> getArticle(@PathVariable("articleId") Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @PostMapping(path = "fragile")
    public Article createFragile(@RequestBody Fragile fragile) {
        return articleService.createFragile(fragile);
    }

    @DeleteMapping(path = "{articleId}")
    public void deleteArticle(@PathVariable("articleId") Long id) {
        articleService.deleteArticle(id);
    }

    @PutMapping(path = "{articleId}")
    public Article updateArticle(@PathVariable("articleId") Long id,@RequestParam(required = false) String name,@RequestParam(required = false) Double prixHT) {
        return articleService.updateArticle(id, name, prixHT);
    }

    @PutMapping(path = "{articleId}/fragile")
    public Article updateFragile(@PathVariable("articleId") Long id,@RequestParam(required = false) String name,@RequestParam(required = false) Double prixHT, @RequestParam(required = false) String emballage) {
        return articleService.updateFragile(id, name, prixHT, emballage);
    }

}
