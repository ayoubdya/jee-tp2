package dev.ayoub.backend.controller;

import dev.ayoub.backend.model.Article;
import dev.ayoub.backend.model.Magasin;
import dev.ayoub.backend.service.MagasinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/magasin")
public class MagasinController {
    @Autowired
    private MagasinService magasinService;

    @GetMapping
    public List<Magasin> getMagasins() {
        return magasinService.getMagasins();
    }

    @GetMapping(path = "{magasinId}")
    public Optional<Magasin> getMagasins(@PathVariable("magasinId") Long id) {
        return magasinService.getMagasin(id);
    }

    @GetMapping(path = "{magasinId}/articles")
    public List<Article> getArticles(@PathVariable("magasinId") Long id) {
        return magasinService.getArticles(id);
    }

    @PostMapping
    public Magasin createMagasin(@RequestBody Magasin magasin) {
        return magasinService.createMagasin(magasin);
    }

    @DeleteMapping(path = "{magasinId}")
    public void deleteMagasin(@PathVariable("magasinId") Long id) {
        magasinService.deleteMagasin(id);
    }

    @PutMapping(path = "{magasinId}")
    public Magasin updateMagasin(@PathVariable("magasinId") Long id, @RequestParam String name) {
        return magasinService.updateMagasin(id, name);
    }

    @PutMapping(path = "{magasinId}/add")
    public Magasin addArticles(@PathVariable("magasinId") Long id, @RequestBody List<Article> articles) {
        return magasinService.addArticles(id, articles);
    }
}


