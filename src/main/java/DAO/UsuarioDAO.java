package DAO;

import DTO.Usuario;

import java.util.List;

public interface UsuarioDAO {
    public void insertUsuario(Usuario usuario);
    public void updateUsuario(Usuario usuario);
    public void deleteUsuario(int id);
    public List<Usuario> listUsuario();
}
