package org.punto1a.Dominio;


import java.util.ArrayList;
import java.util.List;

public class Curso {

    private String nombreCurso;
    private TipoNota tipoNota;
    List<Estudiante> estudiantes = new ArrayList<>();

    public enum TipoNota {CUANTITATIVA, CUALITATIVA}
    public Curso(String nombreCurso, TipoNota tipoNota) {
        this.nombreCurso = nombreCurso;
        this.tipoNota = tipoNota;
    }

    public String getNombreCurso() {return nombreCurso;}
    public TipoNota getTipoNota() {return tipoNota;}
    public List<Estudiante> getEstudiantes() {return estudiantes;}

    public void agregarEstudiante(Estudiante e) {estudiantes.add(e);}
}
