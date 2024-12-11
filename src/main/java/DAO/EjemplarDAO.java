package DAO;

import DTO.Ejemplar;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

import java.util.List;

public interface EjemplarDAO {
    public void insertEjemplar(Ejemplar ejemplar);
    public void updateEmplar(Ejemplar ejemplar);
    public void deleteEmplar(int id);
    public List<Ejemplar> listEmplares();
    public Ejemplar getEmplar(int id);
    public List<Ejemplar> getLibrosEnStockDisponibles(String isbn);
}
