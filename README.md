# Literalura

![img.png](img.png)

## Descripción
Literalura es una aplicación de biblioteca desarrollada en Java que permite obtener y gestionar libros de la
API Gutendex mientras persiste la información en una base de datos PostgreSQL. Este proyecto utiliza tecnologías
modernas como Java JDK 21, Spring Boot y JPA, brindando una experiencia fluida para crear y explorar tu colección
literaria personalizada.

## 🔨 Funcionalidades
- `Obtener libros desde la API de Gutendex:` Importa información de libros directamente desde la API de Gutendex a tu
base de datos.


- `Operaciones en la Base de Datos:` Utiliza PostgreSQL como base de datos principal para persistir y consultar
información sobre libros y autores.

## 📚 Modo de uso
- `Para conseguir un nuevo libro:`
  * **Obtener un nuevo libro:** Obtiene un nuevo libro de la API Gutendex a través de una búsqueda
  por título escrito por el usuario.


- `Para buscar libros en la base de datos:`
  * **Buscar libro por título:** Busca un libro registrado en la base de datos PostgreSQL (libros que fueron
  conseguidos a través de la primera función de este programa).
  
  * **Buscar libros por idioma:** Busca todos los libros de un idioma en específico (actualmente soportado:
  Español, Inglés, Francés y Portugués).
  
  * **Mostrar todos los libros registrados:** Muestra todos los libros registrados en la base de datos.

- `Para buscar autores en la base de datos:`
  * **Buscar autor por nombre:** Busca un autor registrado en la base de datos PostgreSQL (autores que fueron
  conseguidos a través de la primera función de este programa) por el nombre.
  * **Buscar autores vivos en un determinado año:** Busca todos los autores vivos en un año determinado por el usuario
    (tomando en cuenta el año de nacimiento, y de muerte).
  * **Mostrar todos los autores registrados:** Muestra todos los autores registrados en la base de datos.

## ✔️ Tecnologías Utilizadas
* Java JDK 21: Última versión LTS de Java.
* Spring Boot: Framework para crear aplicaciones REST y gestionar la lógica de negocio.
* JPA (Java Persistence API): Para realizar consultas derivadas y manejar interacciones con la base de datos.
* PostgreSQL: Para almacenamiento y gestión de datos.

## 🛠️ Cómo Ejecutar el Proyecto

### Clonar el Repositorio
```
git clone https://github.com/tu-usuario/literalura.git
cd literalura
```

### Configurar la Base de Datos

* Instala PostgreSQL y crea una base de datos para el proyecto.
* Configura la conexión en el archivo application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### Ejecutar desde un IDE

* Abre el proyecto en IntelliJ IDEA u otro IDE compatible.
* Ejecuta la clase principal (generalmente LiteraluraApplication) como una aplicación de Java.

### Interactuar con el Programa

* Usa la terminal del IDE para interactuar con el programa.
* Sigue las opciones del menú en español para realizar búsquedas o registrar información.

## 📂 Estructura del Proyecto

* ***src/main/java***: Contiene el código fuente de la aplicación.
* ***Controllers***: Gestionan las solicitudes y respuestas del usuario.
* ***Services***: Lógica de negocio de la aplicación.
* ***Repositories***: Interfaces para acceso a la base de datos.
* ***Models***: Entidades que representan las tablas de la base de datos.
* ***src/main/resources***: Archivos de configuración.
* ***pom.xml***: Dependencias del proyecto.