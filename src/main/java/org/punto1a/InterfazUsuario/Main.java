package org.punto1a.InterfazUsuario;

import org.punto1a.Dominio.Curso;
import org.punto1a.Servicios.ServicioCurso;
import org.punto1a.Servicios.ServicioEstudiante;
import org.punto1a.Servicios.ServicioNota;
import org.punto1a.Servicios.IServicioPersistencia;
import org.punto1a.Servicios.ServicioPersistencia;

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