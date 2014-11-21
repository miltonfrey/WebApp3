/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojos.Cursoacademico;
import pojos.Estado;
import pojos.EstadoMovilidad;


@Stateless
public class UtilidadServiceImpl implements UtilidadService {

    @PersistenceContext(unitName = "WebApp3PU")
    private EntityManager entityManager;
    
    @Override
    public void crearEstado(Estado e){
        entityManager.persist(e);
    }
    @Override
    public List<Estado> listaEstados(){
        return entityManager.createQuery("select e from Estado e").getResultList();
    }
    @Override
    public void eliminaEstado(Estado e){
        entityManager.remove(e);
                
    }
    
    @Override
    public void crearEstadoMovilidad(EstadoMovilidad e){
        
        entityManager.persist(e);
        
    }
    
    @Override
    public List<EstadoMovilidad> listaEstadosMovilidad(){
         return entityManager.createQuery("select e from EstadoMovilidad e").getResultList();
        
        
    }
    
    
    @Override
    public void eliminaEstadoMovilidad(EstadoMovilidad e){
        entityManager.remove(e);
    }
    
    @Override
    public void crearCursoAcademico(Cursoacademico c){
        
      entityManager.persist(c);
        
    }
    
    @Override
    public List<Cursoacademico> listaCursoAcademico(){
        return entityManager.createQuery("select c from Cursoacademico c").getResultList();
                
    }
    @Override
    public void eliminaCursoAcademico(Cursoacademico c){
        entityManager.remove(c);
    }
    
    
    
}

