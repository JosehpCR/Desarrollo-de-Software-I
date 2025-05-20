package org.punto1a.Dominio.Estudiante;

import org.punto1a.Dominio.Nota.Nota;

public class Estudiante {
    private String nombre;
    private Nota nota;


    public Estudiante(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNombre() {return nombre;}

    public void setNota(Nota nota) {this.nota = nota;}
    public Nota getNota() {return nota;}
}
