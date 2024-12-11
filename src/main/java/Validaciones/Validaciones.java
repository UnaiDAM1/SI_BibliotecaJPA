package Validaciones;

import DTO.Ejemplar;
import DTO.Prestamo;
import DTO.Usuario;

import java.time.LocalDate;
import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * Clase donde controlaremos las validaciones de los datos
 * */

public class Validaciones {

    // Método para validar el ISBN13 de los libros
    public boolean validarISBN13(String isbn13) {
        // Verificar longitud y que solo contenga números
        if (isbn13.length() != 13 || !isbn13.matches("\\d{13}")) {
            return false;
        }

        // Convertir a un arreglo de dígitos
        int[] digits = new int[13];
        for (int i = 0; i < 13; i++) {
            digits[i] = Character.getNumericValue(isbn13.charAt(i));
        }

        // Calcular el checksum
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (i % 2 == 0 ? digits[i] : digits[i] * 3);
        }

        // Calcular el dígito de control esperado
        int checksum = 10 - (sum % 10);
        if (checksum == 10) checksum = 0;

        // Comparar el dígito de control calculado con el último dígito del ISBN
        return checksum == digits[12];
    }

    // Método para validar el DNI de los usuarios
    public boolean validarDNI(String dni) {
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (dni.length() != 9){
            return false;
        } else{
            if (dni.substring(0, 1).equals("X")){
                dni = "0" + dni.substring(1);
            } else if (dni.substring(0, 1).equals("Y")) {
                dni = "1" + dni.substring(1);
            } else if (dni.substring(0, 1).equals("Z")) {
                dni = "2" + dni.substring(1);
            }
            int numero = Integer.parseInt(dni.substring(0, 8));
            if (letras[numero%23].equals(dni.substring(8))){
                return true;
            } else {
                return false;
            }
        }
    }

    // Método para validar los estados de los ejemplares
    public boolean validarEstado(String estado) {
        if (estado.equals("Disponible") || estado.equals("Prestado") || estado.equals("Dañado")){
            return true;
        } else {
            return false;
        }
    }

    // Método para validar que el email acabe en @gmail.com
    public boolean validarEmail(String email) {
        if (email.contains("@")){
            if (email.substring(email.indexOf("@")).equals("@gmail.com")){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Método para validar que la contraseña este entre 6 y 20 caracteres
    public boolean validarPassword(String password) {
        if (password.length() < 6 || password.length() > 20){
            return false;
        } else {
            return true;
        }
    }

    // Método que valida los tipos de usuarios
    public boolean validarTipoUsuario(String tipoUsuario) {
        if (tipoUsuario.equals("normal") || tipoUsuario.equals("administrador")){
            return true;
        } else {
            return false;
        }
    }

    // Método para verificar si un usuario está penalizado
    public boolean estaPenalizado(Usuario usuario){
        if (usuario.getPenalizacionHasta()==null){
            return true;
        } else {
            if (usuario.getPenalizacionHasta().isBefore(LocalDate.now())){
                return true;
            } else {
                return false;
            }
        }

    }

    // Método que verifica si un Usuario tiene 3 prestamos o menos
    public boolean numeroDePrestamos(Usuario usuario, List<Prestamo> prestamos) {
        int cont = 0;
        for (Prestamo p : prestamos){
            if (p.getUsuario().equals(usuario) && p.getFechaDevolucion()==null){
                cont++;
            }
        }
        if (cont < 3){
            return true;
        } else {
            return false;
        }

    }

    // Método para saber si un ejemplar está en estado Disponible
    public boolean ejemplarDisponible(Ejemplar ejemplar){
        if (ejemplar.getEstado().equals("Disponible")){
            return true;
        } else {
            return false;
        }
    }

    // Método para hacer que un usuario no este penalizado más de 45 días
    public boolean penalizacionNoSuperior45(Usuario user){
        if (user.getPenalizacionHasta().isBefore(LocalDate.now().plusDays(45))){
            return true;
        } else {
            return false;
        }
    }
}
