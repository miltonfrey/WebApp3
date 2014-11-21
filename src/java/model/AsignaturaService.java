
package model;

import java.util.List;
import javax.ejb.Local;
import pojos.Asignatura;

@Local
public interface AsignaturaService {
 
     public void crearAsignatura(Asignatura a);
    public List<Asignatura> listarAsignaturas();
    public List<Asignatura> listarAsignaturasPorUniversidad(String codUniversidad);
    public void eliminaAsignatura(Asignatura a);
    public void actualizarAsignatura(Asignatura a);
    public List<Asignatura> listarPorCriterio();
    
}
