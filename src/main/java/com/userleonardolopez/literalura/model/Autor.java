package com.userleonardolopez.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombreCompleto;

    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(DatosAutor datos) {
            this.nombreCompleto = datos.nombreCompleto();
            this.fechaDeNacimiento = datos.fechaDeNacimiento();
            this.fechaDeFallecimiento = datos.fechaDeFallecimiento();
    }

    public Autor() {}

    public void setLibros(List<Libro> libros) {
        libros.forEach(l -> l.setAutor(this));
        this.libros = libros;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + fechaDeNacimiento + " - " + fechaDeFallecimiento + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(getNombreCompleto(), autor.getNombreCompleto());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNombreCompleto());
    }
}
