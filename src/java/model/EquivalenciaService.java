
package model;

import exceptions.ContratoNotFoundException;
import exceptions.FechaIncorrectaException;
import exceptions.MovilidadNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import pojos.Contrato;
import pojos.Equivalencia;

import pojos.Movilidad;

import utils.EquivalenciaRevisada;

@Local
public interface EquivalenciaService {
    public void crearEquivalencia(Equivalencia e);
    public void eliminarEquivalencia(Equivalencia e);
    public void actualizarEquivalencia(Equivalencia e);
    public List<Equivalencia> listarEquivalencias();
    
    
    
       
    
    public void creaContrato(Contrato c);
    public void modificaContrato(Contrato c) throws ContratoNotFoundException;
    public List<Contrato> listaContratos(Movilidad m);
    public void eliminaContrato(Contrato c)throws ContratoNotFoundException;  
    public Contrato findContrato(Integer id) throws ContratoNotFoundException;
   public List<Equivalencia> equivalenciasPublicas(String universidad);
   public List<Object[]> listaObject();
   public int[] totalCreditos(ArrayList<Equivalencia> lista);
   public void confirmarContrato(ArrayList<Equivalencia> lista,Contrato c);
   public ArrayList<Equivalencia> editarContrato(ArrayList<Equivalencia>listaAuxEquivalencias,Contrato c) throws ContratoNotFoundException;
   public void crearContratoDesdeAceptado(ArrayList<Equivalencia>listaAuxEquivalencias,Contrato c, Contrato cNuevo);
   public void compruebaFechaCrearContrato(Contrato c,Date aux)throws FechaIncorrectaException;
   public ArrayList<EquivalenciaRevisada> compararEquivalencias(ArrayList<Equivalencia> listaAuxEquivalencias,ArrayList<Equivalencia> listaAuxEquivalenciasComparado);
    public Contrato verContratoPorEquivalencia(Equivalencia e)throws ContratoNotFoundException;
    public Movilidad buscarMovilidadPorContrato(Contrato c)throws ContratoNotFoundException,MovilidadNotFoundException;
    
}
