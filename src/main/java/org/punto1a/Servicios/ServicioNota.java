package org.punto1a.Servicios;

import org.punto1a.Dominio.Curso;
import org.punto1a.Dominio.Estudiante;
import org.punto1a.Dominio.Nota;

public class ServicioNota {
    public boolean asignarNota(Curso curso, Estudiante estudiante, String valor) {
        if(curso.getTipoNota() == Curso.TipoNota.CUANTITATIVA){
            try {
                double v = Double.parseDouble(valor);
                if(v < 0 || v > 5) return false;
                estudiante.setNota(new Nota(valor));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else{
            if(valor.equalsIgnoreCase("Aprobó") ||
               valor.equalsIgnoreCase("Reprobó") ||
               valor.equalsIgnoreCase("Pendiente")){
               estudiante.setNota(new Nota(valor));

               return true;
            }
            return false;
        }
    }
}
