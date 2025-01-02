package com.userleonardolopez.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReceptorLibros(
        @JsonAlias("count") Integer cantidadDeLibros,
        @JsonAlias("results") List<DatosLibro> libros
) {
}
