
package controller;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import model.UsuarioService;
import exceptions.PasswordIncorrectoException;
import pojos.Usuario;
import exceptions.UsuarioNotFoundException;
import utils.BeanUtilidades;


@Named(value = "autenticarAdminController")
@ViewScoped
public class AutenticarAdminController implements Serializable{

   @Inject
   private BeanUtilidades beanUtilidades;
    
    @EJB
    private UsuarioService usuarioService;
   
   
    
    
    
    private String login;
    private String password;
    
    
    public AutenticarAdminController() {
    }
    


public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    public String autenticarAdmin(){
            Usuario u;
            
             try{
             u=usuarioService.find(getLogin());
             }catch(UsuarioNotFoundException ex){
              beanUtilidades.creaMensaje("login inexistente", FacesMessage.SEVERITY_ERROR);
              return null; 
             }
             
             
             try{ 
            usuarioService.autenticarAdmin(password,u);
            }catch(PasswordIncorrectoException ex){
               beanUtilidades.creaMensaje("password incorrecto", FacesMessage.SEVERITY_ERROR);
               return null;
            }
            
                HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("admin", u);
                return "admin/index.xhtml?faces-redirect=true";
            
        }
            
            
        }
    
