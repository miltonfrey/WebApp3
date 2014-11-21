/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.PasswordIncorrectoException;
import exceptions.UsuarioNotFoundException;
import java.util.List;
import javax.ejb.Local;

import pojos.Usuario;

@Local
public interface UsuarioService {    
    public Usuario find(String nombre)throws UsuarioNotFoundException;
    public void delete(Usuario u) throws UsuarioNotFoundException;
    public List<Usuario> listar();
    
    public void insertarUsuario(Usuario u);
    public void actualizar(Usuario u);
    public String md5Password(String password);
    public void autenticarUsuario(String password,Usuario u) throws PasswordIncorrectoException;
    public void autenticarAdmin(String password,Usuario u) throws PasswordIncorrectoException;
}
