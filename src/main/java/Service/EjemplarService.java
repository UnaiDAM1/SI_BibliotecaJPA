package Service;

import DAO.EjemplarDAO;
import DTO.Ejemplar;
import DTO.Usuario;
import Validaciones.Validaciones;

import java.util.ArrayList;
import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

public class EjemplarService {
    EjemplarDAO ejemplarDAO;
    List<Ejemplar> ejemplares = new ArrayList<>();
    Validaciones validar = new Validaciones();

    public EjemplarService(EjemplarDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
        sincronizar();
    }

    public void sincronizar(){
        ejemplares = ejemplarDAO.listEmplares();
    }

    public void insertEjemplar(Ejemplar ejemplar) throws Exception {
        if (validar.validarEstado(ejemplar.getEstado())) {
            ejemplarDAO.insertEjemplar(ejemplar);
            sincronizar();
        } else {
            throw new Exception("Estado no valido");
        }

    }

    public void updateEjemplar(Ejemplar ejemplar) throws Exception {
        if (validar.validarEstado(ejemplar.getEstado())) {
            ejemplarDAO.updateEmplar(ejemplar);
            sincronizar();
        } else {
            throw new Exception("Estado no valido");
        }
    }

    public void deleteEjemplar(int id){
        ejemplarDAO.deleteEmplar(id);
        sincronizar();
    }

//    public void deleteEjemplarPorISBN(String isbn){
//        for (Ejemplar ejemplar : ejemplarDAO.listEmplares()) {
//            if (ejemplar.getIsbn().getIsbn().equals(isbn)) {
//                ejemplarDAO.deleteEmplar(ejemplar.getId());
//            }
//        }
//    }

    public List<Ejemplar> listEjemplar(){
        return ejemplares;
    }

    public Ejemplar getEjemplar(int id){
        return ejemplarDAO.getEmplar(id);
    }
    public List<Ejemplar> getEjemplaresDisponibles(){
        List<Ejemplar> ejemplaresDisponibles = new ArrayList<>();
        for (Ejemplar ejemplar : ejemplares) {
            if (ejemplar.getEstado().equals("Disponible")) {
                ejemplaresDisponibles.add(ejemplar);
            }
        }
        return ejemplaresDisponibles;
    }
}
