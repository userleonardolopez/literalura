package com.userleonardolopez.literalura.principal;

import com.userleonardolopez.literalura.model.Autor;
import com.userleonardolopez.literalura.model.DatosLibro;
import com.userleonardolopez.literalura.model.Libro;
import com.userleonardolopez.literalura.model.ReceptorLibros;
import com.userleonardolopez.literalura.repository.AutorRepository;
import com.userleonardolopez.literalura.repository.LibroRepository;
import com.userleonardolopez.literalura.service.ConsultadorApi;
import com.userleonardolopez.literalura.service.ConversorApi;

import java.util.*;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSCAR_POR_TITULO = "?search=";

    private String busqueda;
    private String json;
    private ArrayList<Autor> autores = new ArrayList<>();

    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;
    private List<Libro> todosLosLibros;
    private List<Autor> todosLosAutores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.repositorioLibro = libroRepository;
        this.repositorioAutor = autorRepository;
    }

    public void mostrarMenu() {
        // Interfaz de usuario
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" + "\n" +
                "¡Bienvenido a Literalura!");
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    Para conseguir un nuevo libro:
                        1) Obtener un nuevo libro
                    
                    Para buscar libros en la BD:
                        2) Buscar libro por título
                        3) Buscar libros por idioma
                        4) Mostrar todos los libros registrados
                    
                    Para buscar autores en la BD:
                        5) Buscar autor por nombre
                        6) Buscar autores vivos en un determinado año
                        7) Mostrar todos los autores registrados
                    
                        0) Salir
                    """;
            System.out.println(menu);
            while (true) {
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa el número de la opción que desees:" + "\n" +
                            menu);
                }
            }

            switch (opcion) {
                case 1:
                    obtenerNuevoLibro();
                    break;
                case 2:
                    buscarLibroPorTitulo();
                    break;
                case 3:
                    buscarLibrosPorIdioma();
                    break;
                case 4:
                    mostrarTodosLosLibrosRegistrados();
                    break;
                case 5:
                    buscarAutorPorNombre();
                    break;
                case 6:
                    mostrarAutoresVivos();
                    break;
                case 7:
                    mostrarTodosLosAutoresRegistrados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, inténtalo de nuevo.");
            }
        }
    }

    // Busca un libro en la API Gutendex y lo agrega a la BD, según el título proporcionado por el usuario
    private void obtenerNuevoLibro() {
        System.out.println("¿Cuál es el nombre del libro que estás buscando?");
        busqueda = scanner.nextLine().trim().replace(" ", "+");

        json = ConsultadorApi.obtenerJson(URL_BASE + BUSCAR_POR_TITULO + busqueda);

        var libro = obtenerLibroApi(json);

        // Si se obtuvo un libro de la API, evaluará si lo persiste o no
        if (libro != null) {
            Optional<Autor> autorExistente = repositorioAutor
                    .findByNombreCompletoContainsIgnoreCase(libro.getAutor().getNombreCompleto());

            if (autorExistente.isPresent()
                    && autorExistente.get().getNombreCompleto()
                    .equalsIgnoreCase(libro.getAutor().getNombreCompleto())) {
// ****************************************************************************************
                Optional<Libro> libroExistente = repositorioLibro
                        .findByTituloContainsIgnoreCase(libro.getTitulo());

                if (libroExistente.isPresent()
                        && libroExistente.get().getTitulo()
                        .equalsIgnoreCase(libro.getTitulo())) {
                    System.out.println("Este libro ya se encuentra registrado.");
//*****************************************************************************************
                } else {
                    libro.setAutor(autorExistente.get());
                    repositorioLibro.save(libro);
                    System.out.println("¡Libro registrado con éxito!\n" + libro);
                }
            } else {
                repositorioAutor.save(libro.getAutor());
                repositorioLibro.save(libro);
                System.out.println("¡Libro registrado con éxito!\n" + libro);
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("¿Cuál es el nombre del libro que estás buscando?");
        busqueda = scanner.nextLine();

        // Busca un libro en la Base de Datos
        Optional<Libro> libroBuscado = repositorioLibro.findByTituloContainsIgnoreCase(busqueda);

        if (libroBuscado.isPresent()) {
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Lo sentimos, no hemos encontrado un libro con ese nombre.");
        }
    }

    private void buscarLibrosPorIdioma() {
        // Interfaz de usuario
        int opcion = -1;
        String menuIdiomas = """
                1) Español
                2) Inglés
                3) Francés
                4) Portugués
                """;
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*" + "\n" +
                "¿Qué idioma de libros deseas buscar?" + "\n" +
                menuIdiomas);

        while (opcion < 1 || opcion > 4) {
            while (true) {
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa el número de la opción que desees:\n" + menuIdiomas);
                }
            }
            switch (opcion) {
                case 1:
                    busqueda = "Español";
                    break;
                case 2:
                    busqueda = "Inglés";
                    break;
                case 3:
                    busqueda = "Francés";
                    break;
                case 4:
                    busqueda = "Portugués";
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, inténtalo de nuevo.");
            }
        }

        // Busca los libros con el idioma seleccionado en la Base de Datos
        List<Libro> librosEncontrados = repositorioLibro.findByIdiomaContainsIgnoreCase(busqueda);

        if (librosEncontrados.isEmpty()) {
            System.out.println("Lo sentimos, no hemos encontrado ningún libro con ese idioma.");
        } else {
            librosEncontrados.forEach(System.out::println);
        }
    }

    private void mostrarTodosLosLibrosRegistrados() {
        // Obtiene todos los libros de la Base de Datos, y los muestra
        todosLosLibros = repositorioLibro.findAll();
        todosLosLibros.forEach(System.out::println);
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor que desea buscar:");
        busqueda = scanner.nextLine();

        Optional<Autor> autorBuscado = repositorioAutor.findByNombreCompletoContainsIgnoreCase(busqueda);

        if (autorBuscado.isPresent()) {
            System.out.println(autorBuscado.get());
        } else {
            System.out.println("Lo sentimos, no hemos encontrado un autor con ese nombre.");
        }
    }

    private void mostrarAutoresVivos() {
        int fecha;
        System.out.println("Ingrese el año a partir del cual desea consultar los autores vivos:");
        while (true) {
            try {
                busqueda = scanner.nextLine();
                fecha = Integer.parseInt(busqueda); // Año de nacimiento, búsqueda del usuario
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un año para realizar la búsqueda:");
            }
        }

        List<Autor> autoresVivos = repositorioAutor.buscarAutoresVivos(fecha);

        if (autoresVivos.isEmpty()) {
            System.out.println("Lo sentimos, no hemos encontrado ningún autor vivo en ese año.");
        } else {
            autoresVivos.forEach(System.out::println);
        }
    }

    private void mostrarTodosLosAutoresRegistrados() {
        todosLosAutores = repositorioAutor.findAll();
        todosLosAutores.forEach(System.out::println);
    }

    private Libro obtenerLibroApi(String json) {
        var datosRecibidos = ConversorApi.convertirJsonAObjeto(json, ReceptorLibros.class);

        // Crea el objeto Libro sólo si se realizó la conversión de datos de la API
        if (datosRecibidos != null && !datosRecibidos.libros().isEmpty()) {
            List<DatosLibro> listaDeDatos = datosRecibidos.libros();

            // Retorna el primer libro obtenido de la API
            return listaDeDatos.stream()
                    .map(Libro::new)
                    .toList()
                    .getFirst();
        } else {
            System.out.println("Lo sentimos, no hemos encontrado un libro con ese nombre.");
            return null;
        }
    }
}
