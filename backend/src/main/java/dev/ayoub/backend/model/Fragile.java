package dev.ayoub.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fragile extends Article {

    private String emballage;

    @Override
    public double prixTransport() {
        return super.prixTransport() * 2;
    }
}
