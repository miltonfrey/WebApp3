/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Contrato;
import pojos.Equivalencia;

import pojos.Movilidad;

/**
 *
 * @author cba
 */
@Named(value = "equivalenciaDaoImpl")
@RequestScoped
public class EquivalenciaDaoImpl implements EquivalenciaDao{

    @PersistenceContext(unitName = "WebApp3PU")
    private EntityManager entityManager;
    
    public EquivalenciaDaoImpl() {
    }
    
    

    @Override
    public void insertarEquivalencia(Equivalencia e){
        
        entityManager.persist(e);
     
    }
    @Override
    public void actualizarEquivalencia(Equivalencia e){
        
        entityManager.merge(e);
    }
    
    @Override
    public void eliminarEquivalencia(Equivalencia e){
        
        entityManager.remove(e);
        
    }
    
    @Override
    public List<Equivalencia> listarEquivalencias(){
        
        
        return entityManager.createQuery("select e from Equivalencia e").getResultList();
    }
    
   /* @Override
    public void insertarMiembroGrupoAsignatura(MiembroGrupoAsignaturaA m){
        
        sessionFactory.getCurrentSession().saveOrUpdate(m);
        
    }*/
    @Override
    public void creaContrato(Contrato c){
        
        entityManager.persist(c);
    }
    @Override
    
    public void modificaContrato(Contrato c){
        
        entityManager.merge(c);
      
    }
    @Override
    public List<Contrato> listaContratos(Movilidad m){
        
        return entityManager.createQuery("select c from Contrato c where c.idMovilidad=:movilidad").setParameter("movilidad", m).getResultList();
                
    }
    @Override
    public void eliminaContrato(Contrato c){
        
       entityManager.remove(c);
    }
     /*@Override
   public List<Equivalencia> listarEquivalenciasPorContrato(Integer id){
        
        return entityManager.createQuery("select e from Equivalencia e where e.contratoSet.idContrato").setParameter("id", id).getResultList();
    }*/
    
    @Override
    public Contrato findContrato(Integer id){
        
        return (Contrato)entityManager.createQuery("select c from Contrato c where c.idContrato=:contrato").setParameter("contrato", id).getSingleResult();
    }
  
    @Override
   public List<Equivalencia> equivalenciasPublicas(String universidad){
       
       
       
       return entityManager.createQuery("select e from Equivalencia e where e.visible='si' and e.idequivalencia in(select g.idequivalencia from GrupoAsignaturaB g where g.idequivalencia in" +
               "(select m.idGrupoAsignaturaB.idequivalencia from MiembroGrupoAsignaturaB m where m.asignatura.asignaturaPK.nombreUniversidad=:universidad))").setParameter("universidad", universidad).getResultList();
   }
   
   @Override
   public List<Object[]> listaObject(){ 
       //Query q=entityManager.createQuery("select m.asignatura.asignaturaPK.nombreUniversidad from MiembroGrupoAsignaturaB m"); 
       String query="select * from equivalencia";
       return entityManager.createNativeQuery(query).getResultList();
   }
    
   
   
    
}
    

