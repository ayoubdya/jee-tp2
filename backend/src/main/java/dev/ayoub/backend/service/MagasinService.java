package dev.ayoub.backend.service;

import dev.ayoub.backend.model.Article;
import dev.ayoub.backend.model.Magasin;
import dev.ayoub.backend.repository.ArticleRepository;
import dev.ayoub.backend.repository.MagasinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagasinService {

    @Autowired
    private MagasinRepository magasinRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<Magasin> getMagasins() {
        return magasinRepository.findAll();
    }

    public Optional<Magasin> getMagasin(Long id) {
        return magasinRepository.findById(id);
    }

    public void deleteMagasin(Long id) {
        magasinRepository.deleteById(id);
    }

    @Transactional
    public Magasin updateMagasin(Long id, String name) {
        Magasin magasin = magasinRepository.findById(id).orElseThrow(() -> new IllegalStateException("Store doesn't exist"));
        if (name != null && !name.isEmpty()) magasin.setName(name);
        return magasin;
    }

    public Magasin addArticles(Long id, List<Article> articles) {
        Magasin magasin = magasinRepository.findById(id).orElseThrow(() -> new IllegalStateException("Store doesn't exist"));
        List<Article> articleList = articleRepository.saveAll(articles);
        if (articleList != null && !articleList.isEmpty()) {
            System.out.println(1);
            for(Article article : articleList) {
                System.out.println(2);
                magasin.add(article);
            }
        }
        return magasin;
    }

    public Magasin createMagasin(Magasin magasin) {
        return magasinRepository.save(magasin);
    }

    public List<Article> getArticles(Long id) {
        Magasin magasin = magasinRepository.findById(id).orElseThrow(() -> new IllegalStateException("cant find this magasin"));
        return magasin.getArticles();
    }
}
