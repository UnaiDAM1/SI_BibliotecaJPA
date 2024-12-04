package DAO;

import DTO.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    private EntityManager em;

    public LibroDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertLibro(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    @Override
    public void updateLibro(Libro libro) {
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();

    }

    @Override
    public void deleteLibro(String isbn) {
        em.getTransaction().begin();
        Libro lib = em.find(Libro.class, isbn);
        if (lib != null) {
            em.remove(lib);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<Libro> getLibros() {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l", Libro.class);
        return query.getResultList();
    }
}
