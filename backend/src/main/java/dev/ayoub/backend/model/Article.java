package dev.ayoub.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {
    private static double transportFee = 0.05;

    @Id
    @GeneratedValue
    protected Long code;
    protected String name;
    protected double prixHT;

    @ManyToOne
    @JoinColumn(name = "magasin_code", referencedColumnName = "code")
    protected Magasin magasin;

    public double prixTransport() {
        return this.prixHT * transportFee;
    }
}
