package dev.ayoub.backend.repository;

import dev.ayoub.backend.model.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {
}
