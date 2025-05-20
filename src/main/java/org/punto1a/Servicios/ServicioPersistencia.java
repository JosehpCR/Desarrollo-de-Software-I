package org.punto1a.Servicios;

import org.punto1a.Dominio.Curso;
import org.punto1a.Dominio.Estudiante;
import org.punto1a.Dominio.Nota;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioPersistencia implements IServicioPersistencia {
    private final String archivo;

    public ServicioPersistencia(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void guardarCursos(List<Curso> cursos) throws Exception{
        try (PrintWriter out = new PrintWriter(new FileWriter(archivo, true))) {
            for (Curso c : cursos) {
                out.println("CURSO|" + c.getNombreCurso() + "|" + c.getTipoNota());
                for(Estudiante e : c.getEstudiantes()){
                    String nota = (e.getNota() != null) ? e.getNota().getValor() : "";
                    out.println("ESTUDIANTE|" + e.getNombre() + "|"+nota);
                }
            }
        }
    }
    @Override
    public List<Curso> cargarCursos()  throws Exception{
        List<Curso> cursos = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(archivo))){
            String linea;
            Curso actual = null;
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes[0].equals("CURSO")) {
                    Curso.TipoNota tipo = Curso.TipoNota.valueOf(partes[2].toUpperCase());
                    actual = new Curso(partes[1], tipo);
                    cursos.add(actual);
                } else if (partes[0].equals("ESTUDIANTE") && actual != null) {
                    Estudiante e = new Estudiante(partes[1]);
                    if (!partes[2].isEmpty()) e.setNota(new Nota(partes[2]));
                    actual.agregarEstudiante(e);
                }
            }
        }
        return cursos;
    }
    public void imprimirArchivo() {
        File file = new File(archivo);
        if (!file.exists()) {
            System.out.println("El archivo de datos no existe.");
            return;
        }
        System.out.println("\n--- CONTENIDO DEL ARCHIVO " + archivo + " ---");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        System.out.println("--- FIN DEL ARCHIVO ---\n");
    }
}
