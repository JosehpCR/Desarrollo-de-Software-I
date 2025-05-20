package org.punto1a.Main;

import org.punto1a.Dominio.Curso.Curso;
import org.punto1a.InterfazUsuario.Menu;
import org.punto1a.Servicios.Curso.ServicioCurso;
import org.punto1a.Servicios.Estudiante.ServicioEstudiante;
import org.punto1a.Servicios.Nota.ServicioNota;
import org.punto1a.Servicios.Persistencia.IServicioPersistencia;
import org.punto1a.Servicios.Persistencia.ServicioPersistencia;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Curso> cursos = new ArrayList<>();
        ServicioCurso servicioCurso = new ServicioCurso(cursos);
        ServicioEstudiante servicioEstudiante = new ServicioEstudiante();
        ServicioNota servicioNota = new ServicioNota();
        IServicioPersistencia iServicioPersistencia = new ServicioPersistencia("cursos.txt");

        Menu menu = new Menu(servicioCurso, servicioEstudiante, servicioNota, iServicioPersistencia);
        menu.iniciar();


    }
}