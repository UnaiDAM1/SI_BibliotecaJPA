package Service;

import DAO.EjemplarDAO;
import DTO.Ejemplar;

import java.util.ArrayList;
import java.util.List;

public class EjemplarService {
    EjemplarDAO ejemplarDAO;
    List<Ejemplar> ejemplares = new ArrayList<>();

    public EjemplarService(EjemplarDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
        sincronizar();
    }

    public void sincronizar(){
        ejemplares = ejemplarDAO.listEmplares();
    }

    public void insertEjemplar(Ejemplar ejemplar){
        ejemplares.add(ejemplar);
        sincronizar();
    }

    public void updateEjemplar(Ejemplar ejemplar){
        ejemplarDAO.updateEmplar(ejemplar);
        sincronizar();
    }

    public void deleteEjemplar(int id){
        ejemplarDAO.deleteEmplar(id);
        sincronizar();
    }

    public List<Ejemplar> listEjemplar(){
        return ejemplares;
    }
}
