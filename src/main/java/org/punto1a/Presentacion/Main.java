package org.punto1a.Presentacion;


import org.punto1a.Aplicacion.ServicioCurso;
import org.punto1a.Aplicacion.ServicioEstudiante;
import org.punto1a.Aplicacion.ServicioNota;
import org.punto1a.Dominio.Curso;
import org.punto1a.Infraestructura.IServicioPersistencia;
import org.punto1a.Infraestructura.ServicioPersistencia;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Curso> cursos = new ArrayList<>();
        ServicioCurso servicioCurso = new ServicioCurso(cursos);
        ServicioEstudiante servicioEstudiante = new ServicioEstudiante();
        ServicioNota servicioNota = new ServicioNota();
        IServicioPersistencia iServicioPersistencia = new ServicioPersistencia("cursos.txt");


        Menu menu = new Menu(servicioCurso, servicioEstudiante, servicioNota, iServicioPersistencia);
        menu.cargarDatosPrueba(cursos);
        menu.iniciar();


    }
}
