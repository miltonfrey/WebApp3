
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;
import pojos.Usuario;


public interface UsuarioDao {

    public Usuario find(String name);
    public void delete(Usuario u);
    public List<Usuario> listar();
    public void insertarUsuario(Usuario u);
    public void actualizar(Usuario u);
    public String md5Password(String password);
    
        
        
    
}


