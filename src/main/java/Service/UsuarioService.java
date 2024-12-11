package Service;

import DAO.UsuarioDAO;
import DTO.Usuario;
import Validaciones.Validaciones;

import java.util.ArrayList;
import java.util.List;

/*
 * @Autor: Unai Nieto DAM2
 *
 * */

public class UsuarioService {
    UsuarioDAO usuarioDAO;
    List<Usuario> usuarios = new ArrayList<>();
    Validaciones validar = new Validaciones();

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        sincronizar();
    }

    public void sincronizar() {
        usuarios = usuarioDAO.listUsuario();
    }

    public void insertUsuario(Usuario usuario) throws Exception {
        if (validar.validarDNI(usuario.getDni())) {
            if (validar.validarEmail(usuario.getEmail())) {
                if (validar.validarPassword(usuario.getPassword())) {
                    if (validar.validarTipoUsuario(usuario.getTipo())) {
                        usuarioDAO.insertUsuario(usuario);
                        sincronizar();
                    } else {
                        throw new Exception("Tipo de usuario no disponible.");
                    }
                } else {
                    throw new Exception("Contraseña invalida.");
                }
            }else {
                throw new Exception("Email invalido.");
            }
        } else {
            throw new Exception("DNI invalid.");
        }

    }
    public void updateUsuario(Usuario usuario) throws Exception {
        if (validar.validarDNI(usuario.getDni())) {
            if (validar.validarEmail(usuario.getEmail())) {
                if (validar.validarPassword(usuario.getPassword())) {
                    if (validar.validarTipoUsuario(usuario.getTipo())) {
                        usuarioDAO.updateUsuario(usuario);
                        sincronizar();
                    } else {
                        throw new Exception("Tipo de usuario no disponible.");
                    }
                } else {
                    throw new Exception("Contraseña invalida.");
                }
            }else {
                throw new Exception("Email invalido.");
            }
        } else {
            throw new Exception("DNI invalido.");
        }

    }
    public void deleteUsuario(int id) {
        usuarioDAO.deleteUsuario(id);
        sincronizar();
    }
    public List<Usuario> listUsuario() {
        return usuarios;
    }
    public Usuario getUsuario(int id) {
        return usuarioDAO.getUsuario(id);
    }
}
