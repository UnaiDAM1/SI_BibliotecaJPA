package Service;

import DAO.UsuarioDAO;
import DTO.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {
    UsuarioDAO usuarioDAO;
    List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService(List<Usuario> usuarios, UsuarioDAO usuarioDAO) {
        this.usuarios = usuarios;
        this.usuarioDAO = usuarioDAO;
        sincronizar();
    }

    public void sincronizar() {
        usuarios = usuarioDAO.listUsuario();
    }

    public void insertUsuario(Usuario usuario) {
        usuarioDAO.insertUsuario(usuario);
        sincronizar();
    }
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.updateUsuario(usuario);
        sincronizar();
    }
    public void deleteUsuario(int id) {
        usuarioDAO.deleteUsuario(id);
        sincronizar();
    }
    public List<Usuario> listUsuario() {
        return usuarios;
    }
}
