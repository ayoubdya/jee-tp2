package dev.ayoub.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "magasin")
public class Magasin {

    @Id
    @GeneratedValue
    private Long code;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin")
    @JsonIgnore
    private List<Article> articles;

    public void add(Article article) {
        articles.add(article);
    }

    public boolean contains(Article article) {
        return articles.contains(article);
    }
}
