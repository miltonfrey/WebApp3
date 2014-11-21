
package model;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pojos.Usuario;


@Named(value = "usuarioDaoImpl")
@RequestScoped
public class UsuarioDaoImpl implements UsuarioDao{

    
    @PersistenceContext(unitName = "WebApp3PU")
    private EntityManager entityManager;
   
    public UsuarioDaoImpl() {
    }
    
    
    @Override
    public Usuario find(String nombre){
        
        
        return (Usuario) entityManager.createQuery("select u from Usuario u where u.login=:nombre").setParameter("nombre", nombre).getSingleResult();
        
        
        
        
        
    }
    
    @Override
    public void delete(Usuario u) {
        
        
        
        entityManager.remove(u);
        
        
    }
    
    @Override
    public List<Usuario> listar(){
        
        
        return((List<Usuario>)entityManager.createQuery("select u from Usuario u").getResultList());
        
        
    }
    
    
    
    @Override
    public void insertarUsuario(Usuario u){ //throws org.springframework.dao.DataIntegrityViolationException{
        
            String password=md5Password(u.getPassword());
            u.setPassword(password);
            entityManager.persist(u);
            
        
    }
    @Override
    public void actualizar(Usuario u){
        
        entityManager.merge(u);
        
    }
    
    @Override
    public String md5Password(String password){
        
        
        try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
        
        
        
    }
    
    
}

    
    
    
    

