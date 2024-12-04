package DAO;

import DTO.Ejemplar;

import java.util.List;

public interface EjemplarDAO {
    public void insertEmplar(Ejemplar ejemplar);
    public void updateEmplar(Ejemplar ejemplar);
    public void deleteEmplar(int id);
    public List<Ejemplar> listEmplares();
}
