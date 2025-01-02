package com.userleonardolopez.literalura.repository;

import com.userleonardolopez.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String tituloDeLibro);

    List<Libro> findByIdiomaContainsIgnoreCase(String idioma);
}
