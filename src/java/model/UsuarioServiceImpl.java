package model;


import exceptions.PasswordIncorrectoException;
import exceptions.UsuarioNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pojos.Usuario;


@Stateless
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    private UsuarioDao usuarioDao;

  
    @Override
    public Usuario find(String nombre)throws UsuarioNotFoundException{
        
        Usuario u=usuarioDao.find(nombre);
        if(u==null){
            throw new UsuarioNotFoundException();
        }
        
        return u;
    }
    
    @Override
    public void delete(Usuario u) throws UsuarioNotFoundException{
        
        
        u=find(u.getLogin());
        
        usuarioDao.delete(u);
       
    }
    
    @Override
    public List<Usuario>listar(){
        
        List<Usuario> lista=usuarioDao.listar();
        Usuario u=usuarioDao.find("admin");
        lista.remove(u);
        return lista;
    }
    
    
    @Override
    public void insertarUsuario(Usuario u){
        
        
            
            usuarioDao.insertarUsuario(u);
}
    @Override
    public void actualizar(Usuario u){
        
        
        usuarioDao.actualizar(u);
        
        
    }
    @Override
    public String md5Password(String password){
        
       return usuarioDao.md5Password(password);
    }

    
    @Override
    public void autenticarUsuario(String password,Usuario u)throws PasswordIncorrectoException{
        
        password=md5Password(password);
            String pass=u.getPassword();
            if((pass.equals(password)==false)||u.getTipoUsuario()!=1){
                
               throw new PasswordIncorrectoException();
            }
        
    
}

   @Override
   public void autenticarAdmin(String password,Usuario u) throws PasswordIncorrectoException{
       
       password=md5Password(password);
            String pass=u.getPassword();
            if((pass.equals(password)==false)||u.getTipoUsuario()!=0){
                
               throw new PasswordIncorrectoException();
            }
       
   }
    
    
}