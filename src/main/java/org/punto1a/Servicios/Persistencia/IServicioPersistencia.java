package org.punto1a.Servicios.Persistencia;

import org.punto1a.Dominio.Curso.Curso;

import java.util.List;

public interface IServicioPersistencia {
    void guardarCursos(List<Curso> cursos) throws Exception;
    List<Curso> cargarCursos()  throws Exception;

}
