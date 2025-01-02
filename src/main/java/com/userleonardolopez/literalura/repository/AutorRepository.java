package com.userleonardolopez.literalura.repository;

import com.userleonardolopez.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombreCompletoContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeFallecimiento >= :fecha AND a.fechaDeNacimiento <= :fecha")
    List<Autor> buscarAutoresVivos(int fecha);
}