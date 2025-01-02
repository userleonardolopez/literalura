package com.userleonardolopez.literalura.model;

import jakarta.persistence.*;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idioma;
    private Integer descargas;

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        try {
            this.autor = datos.autores().stream()
                    .map(Autor::new)
                    .collect(Collectors.toList())
                    .getFirst();
        } catch (NoSuchElementException e) {
            this.autor = new Autor();
        }
        switch (datos.idiomas().getFirst()) {
            case ("es"):
                this.idioma = "Español";
                break;
            case ("en"):
                this.idioma = "Inglés";
                break;
            case ("fr"):
                this.idioma = "Francés";
                break;
            case ("pt"):
                this.idioma = "Portugués";
                break;
            default:
                this.idioma = datos.idiomas().getFirst();
        }
        this.descargas = datos.descargas();
    }

    public Libro() {}

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    @Override
    public String toString() {
        return  "*-*-*-*-*-*-*-LIBRO-*-*-*-*-*-*-*\n" +
                "* Título: " + titulo + "\n" +
                "* Autor: " + autor + "\n" +
                "* Idioma: " + idioma + "\n" +
                "* Número de descargas: " + descargas + "\n" +
                "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(getId(), libro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
