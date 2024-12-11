import DAO.*;
import DTO.Ejemplar;
import DTO.Libro;
import DTO.Prestamo;
import DTO.Usuario;
import Service.EjemplarService;
import Service.LibroService;
import Service.PrestamoService;
import Service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

/*
* @Autor: Unai Nieto DAM2
*
* En la clase main se inicia todo el programa de la gestion de la biblioteca, lo gestionaremos gracias
* a un menú.
* */

public class Main {
    // Creación de los variables EntityManager
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
    static EntityManager em = emf.createEntityManager();

    // Creación de las clases service para la gestión de la base de datos
    static EjemplarService ejemplarService = new EjemplarService(new EjemplarDAOImpl(em));
    static LibroService libroService = new LibroService(new LibroDAOImpl(em));
    static PrestamoService prestamoService = new PrestamoService(new PrestamoDAOImpl(em));
    static UsuarioService usuarioService = new UsuarioService(new UsuarioDAOImpl(em));
    static GestionPrestamos gestionPrestamos = new GestionPrestamos(prestamoService, ejemplarService, usuarioService);

    // Menú de los usuarios tipo administrador
    public static void menuGestionAdmin(Usuario usuario) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opc;

        do {
            System.out.println("-------------ADMINISTRADOR-------------");
            System.out.println("---------Listar---------");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Listar libros");
            System.out.println("3. Listar prestamos");
            System.out.println("4. Listar ejemplares");

            System.out.println("---------Crear---------");
            System.out.println("5. Crear usuario");
            System.out.println("6. Crear libro");
            System.out.println("7. Crear ejemplar");

            System.out.println("---------Actualizar---------");
            System.out.println("8. Actualizar usuario");
            System.out.println("9. Actualizar libro");
            System.out.println("10. Cambiar ejemplar a dañado");

            System.out.println("---------Eliminar---------");
            System.out.println("11. Eliminar usuario");
            System.out.println("12. Eliminar libro");
            System.out.println("13. Eliminar prestamo");
            System.out.println("14. Eliminar ejemplar");

            System.out.println("---------Gestión Prestamos---------");
            System.out.println("15. Hacer prestamo");
            System.out.println("16. Devolver libro");

            System.out.println("---------Stock---------");
            System.out.println("17. Stock de libros");

            System.out.println("----------SALIR----------");
            System.out.println("0. Cerrar sesión");
            System.out.println("Introduzca una opción: ");
            opc = sc.nextInt();

            sc.nextLine();
            switch (opc) {
                case 1:
                    System.out.println(usuarioService.listUsuario());
                    break;
                case 2:
                    System.out.println(libroService.getLibros());
                    break;
                case 3:
                    System.out.println(prestamoService.listPrestamo());
                    break;
                case 4:
                    System.out.println(ejemplarService.listEjemplar());
                    break;
                case 5:
                    System.out.println("Introduzca su DNI: ");
                    String dni = sc.nextLine();
                    System.out.println("Introduzca el nombre del usuario: ");
                    String nombre = sc.nextLine();
                    System.out.println("Intrduzca su email: ");
                    String email = sc.nextLine();
                    System.out.println("Introduzca su contraseña");
                    String contrasena = sc.nextLine();
                    System.out.println("Introduzca el tipo de usuario que será (normal o administrador:");
                    String tipoUsuario = sc.nextLine();
                    usuarioService.insertUsuario(new Usuario(dni, nombre, email, contrasena, tipoUsuario));
                    System.out.println("Usuario añadido con exito.");
                    break;
                case 6:
                    System.out.println("Introduzca el ISBN del libro: ");
                    String isbn = sc.nextLine();
                    System.out.println("Introduzca el título del libro: ");
                    String titulo = sc.nextLine();
                    System.out.println("Introduzca el autor del libro: ");
                    String autor = sc.nextLine();
                    libroService.insertLibro(new Libro(isbn, titulo, autor));
                    break;
                case 7:
                    System.out.println(libroService.getLibros());
                    System.out.println("Introduzca el isbn del libro que quiere crear un ejemplar: ");
                    String isbnCrearEjemplar = sc.nextLine();
                    Libro libroCrearEjemplar = libroService.getLibroPorISBN(isbnCrearEjemplar);
                    ejemplarService.insertEjemplar(new Ejemplar(libroCrearEjemplar));
                    System.out.println("Ejemplar creado con éxito.");
                    break;
                case 8:
                    System.out.println(usuarioService.listUsuario());
                    System.out.println("Introduzca el ID del usuario que desea actualizar: ");
                    int idUserUpdate = sc.nextInt();
                    Usuario userUpdate = usuarioService.getUsuario(idUserUpdate);
                    sc.nextLine();
                    System.out.println("Nuevo DNI: ");
                    userUpdate.setDni(sc.nextLine());
                    System.out.println("Nuevo Nombre: ");
                    userUpdate.setNombre(sc.nextLine());
                    System.out.println("Nuevo Email: ");
                    userUpdate.setEmail(sc.nextLine());
                    System.out.println("Nuevo Contraseña: ");
                    userUpdate.setPassword(sc.nextLine());
                    System.out.println("Nuevo Tipo de usuario: ");
                    userUpdate.setTipo(sc.nextLine());
                    usuarioService.updateUsuario(userUpdate);
                    System.out.println("Usuario actualizado con exito.");
                    break;
                case 9:
                    System.out.println(libroService.getLibros());
                    System.out.println("Introduzca el ISBN del libro a actualizar: ");
                    String isbnLibroUpdate = sc.nextLine();
                    Libro libroUpdate = libroService.getLibroPorISBN(isbnLibroUpdate);
                    sc.nextLine();
                    System.out.println("Nuevo titulo: ");
                    libroUpdate.setTitulo(sc.nextLine());
                    System.out.println("Nuevo autor: ");
                    libroUpdate.setAutor(sc.nextLine());
                    libroService.updateLibro(libroUpdate);
                    System.out.println("Libro actualizado con exito.");
                    break;
                case 10:
                    System.out.println(ejemplarService.getEjemplaresDisponibles());
                    System.out.println("Introduzca el ID del ejemplar dañado: ");
                    int idEjemplarUpdate = sc.nextInt();
                    Ejemplar ejemplarDañado = ejemplarService.getEjemplar(idEjemplarUpdate);
                    if (ejemplarDañado.getEstado().equals("Disponible")){
                        ejemplarDañado.setEstado("Dañado");
                        ejemplarService.updateEjemplar(ejemplarDañado);
                    }
                    System.out.println("Ejemplar actualizado con exito.");
                    break;
                case 11:
                    System.out.println(usuarioService.listUsuario());
                    System.out.println("Introduzca el ID del usuario a eliminar: ");
                    int idUserDelete = sc.nextInt();
                    usuarioService.deleteUsuario(idUserDelete);
                    System.out.println("Usuario eliminado con exito.");
                    break;
                case 12:
                    // Para eliminar un libro será un poco más dificil ya que tendremos que antes eliminar
                    // todos los prestamos y no nos de error al eliminar en cascada
                    System.out.println(libroService.getLibros());
                    System.out.println("Introduzca el ISBN del libro a eliminar: ");
                    String isbnLibroDelete = sc.nextLine();
                    prestamoService.deletePrestamosByLibro(isbnLibroDelete);
                    libroService.deleteLibro(isbnLibroDelete);
                    System.out.println("Libro eliminado con exito.");
                    break;
                case 13:
                    System.out.println(prestamoService.listPrestamo());
                    System.out.println("Introduzca el ID del prestamo a eliminar: ");
                    int idPrestamoDelete = sc.nextInt();
                    prestamoService.deletePrestamo(idPrestamoDelete);
                    System.out.println("Prestamo eliminado con exito.");
                    break;
                case 14:
                    System.out.println(ejemplarService.listEjemplar());
                    System.out.println("Introduzca el ID del ejemplar a eliminar: ");
                    int idEjemplarDelete = sc.nextInt();
                    ejemplarService.deleteEjemplar(idEjemplarDelete);
                    System.out.println("Ejemplar eliminado con exito.");
                    break;
                case 15:
                    System.out.println("Introduzca el ID del usuario que hará el prestamo: ");
                    int idUsuario = sc.nextInt();
                    System.out.println("Introduzca el ID de un ejemplar disponible: ");
                    int idEjemplar = sc.nextInt();
                    Usuario user = usuarioService.getUsuario(idUsuario);
                    Ejemplar ejem = ejemplarService.getEjemplar(idEjemplar);
                    gestionPrestamos.hacerPrestamos(new Prestamo(user, ejem));
                    System.out.println("Prestamo creado con exito.");
                    break;
                case 16:
                    System.out.println("Introduzca el ID del prestamo a devolver: ");
                    int idPrestamo = sc.nextInt();
                    Prestamo prestamo = prestamoService.getPrestamo(idPrestamo);
                    gestionPrestamos.devolverPrestamo(prestamo);
                    break;
                case 17:
                    System.out.println(libroService.getLibros());
                    System.out.println("Introduzca el ISBN del libro que quieras saber el stock: ");
                    String isbnStock = sc.nextLine();
                    int cont = 0;
                    for (Ejemplar e : libroService.getLibroPorISBN(isbnStock).getEjemplars()) {
                        if (e.getEstado().equals("Disponible")) {
                            System.out.println(e);
                            cont++;
                        }
                    }
                    System.out.println("Stock: " + cont);
                default:
                    break;
            }

        } while (opc != 0);

    }

    // Menú de los usuarios tipo normal
    public static void menuGestionNormal(Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        int opc;

        do {
            System.out.println("-------------EL REFUGIO DEL LIBRO-------------");
            System.out.println("1. Ver mis prestamos.");
            System.out.println("0. Cerrar sesión.");
            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.println(prestamoService.listPrestamosPorIDMemoria(usuario.getId()));
            }
        } while (opc != 0);

    }

    // Clase main que inicia todo el programa y el menu de inicio de sesión
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opc;

        do {
            System.out.println("-------------Inicio de sesión-------------");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opción: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    System.out.println("Ingrese el nombre del usuario: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese la contraseña: ");
                    String contrasena = sc.nextLine();
                    System.out.println("Ingrese el ID: ");
                    int id = sc.nextInt();
                    // Con este for veo si las credenciales introducidas coinciden con alguno de los usuarios
                    // guardados en la base de datos
                    for (Usuario u : usuarioService.listUsuario()) {
                        if (u.getId() == id) {
                            if (u.getPassword().equals(contrasena) && u.getNombre().equals(nombre)) {
                                if (u.getTipo().equals("administrador")) {
                                    menuGestionAdmin(u);
                                } else if (u.getTipo().equals("normal")) {
                                    menuGestionNormal(u);
                                }
                            }
                        }
                    }
            }
        } while (opc != 0);

    }
}
