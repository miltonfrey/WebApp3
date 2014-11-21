/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.ContratoNotFoundException;
import exceptions.FechaIncorrectaException;
import exceptions.MovilidadNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pojos.Asignatura;
import pojos.Contrato;
import pojos.Equivalencia;

import pojos.MiembroGrupoAsignaturaA;
import pojos.MiembroGrupoAsignaturaB;
import pojos.Movilidad;

import utils.EquivalenciaRevisada;


@Stateless
public class EquivalenciaServiceImpl implements EquivalenciaService{

    @Inject
    private EquivalenciaDao equivalenciaDao;
    
   
    
    
    @Override
    public void crearEquivalencia(Equivalencia e){
        
        equivalenciaDao.insertarEquivalencia(e);
    }
    
    @Override
    public void eliminarEquivalencia(Equivalencia e){
        
        equivalenciaDao.eliminarEquivalencia(e);
        
    }
    @Override
    public void actualizarEquivalencia(Equivalencia e){
        
        equivalenciaDao.actualizarEquivalencia(e);
        
    }
    
    @Override
    
    public List<Equivalencia> listarEquivalencias(){
        
        return equivalenciaDao.listarEquivalencias();
    }
    
    
    
    
 
    
    @Override
    public void creaContrato(Contrato c){
        equivalenciaDao.creaContrato(c);
        
    }
    
    @Override
    public void modificaContrato(Contrato c)throws ContratoNotFoundException{
        
        c=findContrato(c.getIdContrato());
        equivalenciaDao.modificaContrato(c);
    }
    @Override
    
    public List<Contrato> listaContratos(Movilidad m){
        return equivalenciaDao.listaContratos(m);
    }
    @Override
    public void eliminaContrato(Contrato c)throws ContratoNotFoundException{
        
        c=findContrato(c.getIdContrato()); 
        equivalenciaDao.eliminaContrato(c);
       
    }
    /*@Override
    
    public List<Equivalencia> listarEquivalenciasPorContrato(Integer id){
        List<Equivalencia> listaEquivalenciasPorcontrato=equivalenciaDao.listarEquivalenciasPorContrato(id);
        
        for(Equivalencia e:listaEquivalenciasPorcontrato){ 
            
        Hibernate.initialize(e.getGrupoAsignaturaB().getMiembroGrupoAsignaturaBs());
        Iterator i=e.getGrupoAsignaturaB().getMiembroGrupoAsignaturaBs().iterator();
        while(i.hasNext()){
            MiembroGrupoAsignaturaB m=(MiembroGrupoAsignaturaB)i.next();
            Hibernate.initialize(m.getAsignatura());
        }
        Hibernate.initialize(e.getGrupoAsignaturaA().getMiembroGrupoAsignaturaAs());
        Iterator j=e.getGrupoAsignaturaA().getMiembroGrupoAsignaturaAs().iterator();
        while(j.hasNext()){
            MiembroGrupoAsignaturaA m=(MiembroGrupoAsignaturaA)j.next();
            Hibernate.initialize(m.getAsignatura());
        }
        
        
        
        
        }
        return listaEquivalenciasPorcontrato;
        
    }*/
    
    
    @Override
    public Contrato findContrato(Integer id) throws ContratoNotFoundException{
        
        Contrato c=equivalenciaDao.findContrato(id);
        if (c==null)
                throw new ContratoNotFoundException();
        
        /*Hibernate.initialize(c.getEquivalencias());
        for(Equivalencia e:c.getEquivalencias()){ 
            
        Hibernate.initialize(e.getGrupoAsignaturaB().getMiembroGrupoAsignaturaBs());
        Iterator i=e.getGrupoAsignaturaB().getMiembroGrupoAsignaturaBs().iterator();
        while(i.hasNext()){
            MiembroGrupoAsignaturaB m=(MiembroGrupoAsignaturaB)i.next();
            Hibernate.initialize(m.getAsignatura());
        }
        Hibernate.initialize(e.getGrupoAsignaturaA().getMiembroGrupoAsignaturaAs());
        Iterator j=e.getGrupoAsignaturaA().getMiembroGrupoAsignaturaAs().iterator();
        while(j.hasNext()){
            MiembroGrupoAsignaturaA m=(MiembroGrupoAsignaturaA)j.next();
            Hibernate.initialize(m.getAsignatura());
            
        }
        
        
        
        
    }*/
           return c; 
}       
  
      
    @Override  
    public List<Equivalencia> equivalenciasPublicas(String Universidad){
        
        return equivalenciaDao.equivalenciasPublicas(Universidad);
        
       /* for(Equivalencia e:listaEquivalencias){
            Hibernate.initialize(e.getGrupoAsignaturaA().getMiembroGrupoAsignaturaAs());
            i=e.getGrupoAsignaturaA().getMiembroGrupoAsignaturaAs().iterator();
            while(i.hasNext()){
                MiembroGrupoAsignaturaA m=(MiembroGrupoAsignaturaA)i.next();
                Hibernate.initialize(m.getAsignatura());
            }
            
             Hibernate.initialize(e.getGrupoAsignaturaB().getMiembroGrupoAsignaturaBs());
            i=e.getGrupoAsignaturaB().getMiembroGrupoAsignaturaBs().iterator();
            while(i.hasNext()){
                MiembroGrupoAsignaturaB m=(MiembroGrupoAsignaturaB)i.next();
                Hibernate.initialize(m.getAsignatura());
            }
        }*/
        
    }
    
    @Override
    public List<Object[]> listaObject(){
        
        return equivalenciaDao.listaObject();
    }
    
    
    @Override
    public int[] totalCreditos(ArrayList<Equivalencia> lista){
        
         int a=0;
         int b=0;
         
        for(Equivalencia e:lista){
            Iterator i=e.getMiembroGrupoAsignaturaASet().iterator();
            while(i.hasNext()){
                MiembroGrupoAsignaturaA mA=(MiembroGrupoAsignaturaA)i.next();
                a=a+mA.getAsignatura().getCreditos();
            }
        }
        
        for(Equivalencia e:lista){
            Iterator i=e.getMiembroGrupoAsignaturaBSet().iterator();
            while(i.hasNext()){
                MiembroGrupoAsignaturaB mB=(MiembroGrupoAsignaturaB)i.next();
                b=b+mB.getAsignatura().getCreditos();
            }
        }
        
        return new int[]{a,b};
    }
    
    @Override
    public void confirmarContrato(ArrayList<Equivalencia> listaAuxEquivalencias,Contrato c){
        
        Set s1=new HashSet<Equivalencia>();
        for(Equivalencia e:listaAuxEquivalencias){
          
            s1.add(e);
            //e.getContratos().add(c); //No hace falta
            
            crearEquivalencia(e);
           
             
        }
        c.setEquivalenciaSet(s1);
        creaContrato(c);
        
    }
    
    @Override
    public ArrayList<Equivalencia> editarContrato(ArrayList<Equivalencia> listaAuxEquivalencias,Contrato c) throws ContratoNotFoundException{
        
        ArrayList<Equivalencia> listaCopia=new ArrayList<Equivalencia>();
               
        
        for(Equivalencia e:c.getEquivalenciaSet()){
            
            if(listaAuxEquivalencias.contains(e)==false){
              
                   listaCopia.add(e);
          
        }
        }
        
        for(Equivalencia e:listaCopia){
           
            c.getEquivalenciaSet().remove(e);
            //e.getContratos().remove(c);
            
            //equivalenciaService.actualizarEquivalencia(e);
            modificaContrato(c);
            
            //eliminarEquivalencia(e);
        
        }
        
        
        for(Equivalencia e2:listaAuxEquivalencias){
            
         if(c.getEquivalenciaSet().contains(e2)==false){   
           
            //e.getContratos().add(c);
            c.getEquivalenciaSet().add(e2);
            crearEquivalencia(e2);
            
         
        }
       
    }
       
            c.setEstado("pendiente");
            c.setFecha(Calendar.getInstance().getTime());
            modificaContrato(c);
            return listaCopia;
        
    }
    
    
 
    @Override
     public void crearContratoDesdeAceptado(ArrayList<Equivalencia>listaAuxEquivalencias,Contrato c,Contrato cNuevo){
       
        
        
        for(Equivalencia e:listaAuxEquivalencias){
            
         if(c.getEquivalenciaSet().contains(e)==true){   
            
            cNuevo.getEquivalenciaSet().add(e);
            
         
           
        }else{
             
            crearEquivalencia(e);
            
             cNuevo.getEquivalenciaSet().add(e);
         }
       
    }
        
        creaContrato(cNuevo);
        
    }
     
     @Override
     public void compruebaFechaCrearContrato(Contrato c,Date aux)throws FechaIncorrectaException{
         
         
         
     }
    
    @Override 
    public ArrayList<EquivalenciaRevisada> compararEquivalencias(ArrayList<Equivalencia> listaAuxEquivalencias,ArrayList<Equivalencia> listaAuxEquivalenciasComparado){
        
        ArrayList<EquivalenciaRevisada> listaRevisada=new ArrayList<EquivalenciaRevisada>();
        
        loop:
        for(Equivalencia e:listaAuxEquivalencias){
            EquivalenciaRevisada er=new EquivalenciaRevisada(e);
            er.setIgual(true);
            ArrayList<Asignatura> listaAsignaturas=new ArrayList<Asignatura>();
            ArrayList<Asignatura> listaAsignaturasB=new ArrayList<Asignatura>();
            
            
            Iterator i=e.getMiembroGrupoAsignaturaASet().iterator();
            while(i.hasNext()){
                MiembroGrupoAsignaturaA m=(MiembroGrupoAsignaturaA)i.next();
                listaAsignaturas.add(m.getAsignatura());   
            }
            
            
            Iterator j=e.getMiembroGrupoAsignaturaBSet().iterator();
            while(j.hasNext()){
                MiembroGrupoAsignaturaB mb=(MiembroGrupoAsignaturaB)j.next();
                listaAsignaturasB.add(mb.getAsignatura());
            }     
            
            
            loopB: 
                  for(Equivalencia eComp:listaAuxEquivalenciasComparado){
                      
                       ArrayList<Asignatura> listaAsignaturasComp=new ArrayList<Asignatura>();
                       i=eComp.getMiembroGrupoAsignaturaASet().iterator();
                            while(i.hasNext()){
                            MiembroGrupoAsignaturaA m=(MiembroGrupoAsignaturaA)i.next();
                            listaAsignaturasComp.add(m.getAsignatura());
                
                        }
                             
                           if(listaAsignaturas.size()==listaAsignaturasComp.size()){
                                 if(contiene(listaAsignaturas, listaAsignaturasComp)){
                              
                                   System.out.println(listaAsignaturasComp.get(0).getNombreAsignatura());
                                   ArrayList<Asignatura> listaAsignaturasCompB=new ArrayList<Asignatura>();
                                      j=eComp.getMiembroGrupoAsignaturaBSet().iterator();
                                      
                                       while(j.hasNext()){
                                       MiembroGrupoAsignaturaB mb=(MiembroGrupoAsignaturaB)j.next();
                                       listaAsignaturasCompB.add(mb.getAsignatura());
                           }     
                                      
                                      if(listaAsignaturasB.size()==listaAsignaturasCompB.size()){
                                          if(contiene(listaAsignaturasB, listaAsignaturasCompB)){
                                                er.setIgual(false);
                                                listaRevisada.add(er);
                                                continue loop;
                                          }
                                      }
                               
                               
                           }
                           
                  }
                           
               }
                listaRevisada.add(er);
            }
            return listaRevisada;
        }
       
     
 
     
  /*   @Override
     public ArrayList<EquivalenciaRevisada> compararEquivalencias(ArrayList<Equivalencia> listaAuxEquivalencias,ArrayList<Equivalencia> listaAuxEquivalenciasComparado){
         
         ArrayList<EquivalenciaRevisada> listaRevisada=new ArrayList<EquivalenciaRevisada>();
         
         for(Equivalencia e:listaAuxEquivalencias){
             EquivalenciaRevisada er=new EquivalenciaRevisada(e);
             
             if(listaAuxEquivalenciasComparado.contains(e)==false){
                 er.setIgual(true);
             }
             listaRevisada.add(er);
         }
          
         return listaRevisada;
        
     }*/
     
     
     public boolean contiene(ArrayList<Asignatura> listaA, ArrayList<Asignatura> listaB){
        
         
         loopA:
         for(Asignatura a:listaA){
             for(Asignatura b:listaB){
                 if(a.getNombreAsignatura().equals(b.getNombreAsignatura()))
                 continue loopA;    
             }
             return false;
         }
         
         return true;
     }
     
     @Override
     public Contrato verContratoPorEquivalencia(Equivalencia e) throws ContratoNotFoundException{
         
         Contrato c=null;
         
         //Hibernate.initialize(e.getContratos());
         Iterator i=e.getContratoSet().iterator();
         while(i.hasNext()){
             if(c==null){
                 c=(Contrato)i.next();
             }else{
                 Contrato aux=(Contrato)i.next();
                 if (c.getFecha().compareTo(aux.getFecha())<0){
                     
                 c=aux;
                     
                 
             }
             
         }
         
     }
    if(c==null)
        throw new ContratoNotFoundException();
         
     return c;
     
}
     
     @Override
     public Movilidad buscarMovilidadPorContrato(Contrato c)throws ContratoNotFoundException,MovilidadNotFoundException{
        c=findContrato(c.getIdContrato());
        //Hibernate.initialize(c.getMovilidad());
        if(c.getIdMovilidad()==null)
            throw new MovilidadNotFoundException();
        //Hibernate.initialize(c.getMovilidad().getUniversidad());
        return c.getIdMovilidad();
     }
             
    
   
}        
          

