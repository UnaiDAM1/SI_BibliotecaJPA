import DAO.*;
import DTO.Usuario;
import Service.EjemplarService;
import Service.LibroService;
import Service.PrestamoService;
import Service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {

    public static void menuGestionAdmin(){
        Scanner sc = new Scanner(System.in);

    }

    public static void menuGestionNormal(){
        Scanner sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        EjemplarService ejemplarService = new EjemplarService(new EjemplarDAOImpl(em));
        LibroService libroService = new LibroService(new LibroDAOImpl(em));
        PrestamoService prestamoService = new PrestamoService(new PrestamoDAOImpl(em));
        UsuarioService usuarioService = new UsuarioService(new UsuarioDAOImpl(em));

        Scanner sc = new Scanner(System.in);
        int opc;

        do {
            System.out.println("\n-------------Inicio de sesión-------------");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear nuevo usuario");
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
                    for (Usuario u : usuarioService.listUsuario()){
                        if (u.getNombre().equals(nombre)){
                            if (u.getPassword().equals(contrasena)){
                                if (u.getTipo().equals("admin")){
                                    menuGestionAdmin();
                                } else if (u.getTipo().equals("normal")){
                                    menuGestionNormal();
                                }
                            }
                        }
                    }
            }
        } while (opc != 0);
        
    }
}
