package org.punto1a.InterfazUsuario;

import org.punto1a.Dominio.Curso;
import org.punto1a.Dominio.Estudiante;
import org.punto1a.Servicios.ServicioCurso;
import org.punto1a.Servicios.ServicioEstudiante;
import org.punto1a.Servicios.ServicioNota;
import org.punto1a.Servicios.IServicioPersistencia;
import org.punto1a.Servicios.ServicioPersistencia;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private ServicioCurso servicioCurso;
    private ServicioEstudiante servicioEstudiante;
    private ServicioNota servicioNota;
    private IServicioPersistencia iServicioPersistencia;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(ServicioCurso servicioCurso, ServicioEstudiante servicioEstudiante, ServicioNota servicioNota, IServicioPersistencia iServicioPersistencia) {
        this.servicioCurso = servicioCurso;
        this.servicioEstudiante = servicioEstudiante;
        this.servicioNota = servicioNota;
        this.iServicioPersistencia = iServicioPersistencia;
    }
    public void iniciar() {
        while (true) {
            System.out.println("\n1. Crear curso");
            System.out.println("2. Agregar estudiante a curso");
            System.out.println("3. Registrar nota");
            System.out.println("4. Listar cursos");
            System.out.println("5. Listar estudiantes");
            System.out.println("6. Listar notas");
            System.out.println("7. Guardar");
            System.out.println("8. Cargar");
            System.out.println("9. Mostrar archivo");
            System.out.println("10. Salir");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1": crearCurso(); break;
                case "2": agregarEstudiante(); break;
                case "3": registrarNota(); break;
                case "4": listarCursos(); break;
                case "5": listarEstudiantes(); break;
                case "6": listarNotas(); break;
                case "7": guardar(); break;
                case "8": cargar(); break;
                case "9": mostrarContenidoArchivo(); break;
                case "10": return;
                default: System.out.println("Opción inválida.");

            }
        }
    }
    private void crearCurso() {
        System.out.println("Nombre del curso: ");
        String nombreCurso = scanner.nextLine();
        System.out.println("Tipo de nota (cuantitativa/cualitativa)");
        String tipoN = scanner.nextLine().trim().toUpperCase();
        try{
            Curso.TipoNota tipoNota = Curso.TipoNota.valueOf(tipoN);
            servicioCurso.crearCurso(nombreCurso, tipoNota);
        } catch (Exception e) {
            System.out.println("Tipo de nota inválido");
        }
    }
    private void agregarEstudiante() {
        Curso curso = seleccionarCurso();
        if(curso == null)return;
        System.out.println("Nombre del estudiante: ");
        String nombreEstudiante = scanner.nextLine();
        servicioEstudiante.agregarEstudiante(curso, nombreEstudiante);
    }
    private void registrarNota() {
        Curso curso = seleccionarCurso();
        if(curso == null)return;
        Estudiante estudiante = seleccionarEstudiante(curso);
        if(estudiante == null)return;
        System.out.println("Ingrese nota: ");
        String valor = scanner.nextLine();
        if(!servicioNota.asignarNota(curso,estudiante,valor)) System.out.println("Nota invalida");
    }
    private void listarCursos() {
        int i =1;
        for (Curso c: servicioCurso.getCursos()){
            System.out.println(i++ +". " + c.getNombreCurso() + " [" + c.getTipoNota() + "]");
        }
    }
    private void listarEstudiantes() {
        Curso curso = seleccionarCurso();
        if(curso == null)return;
        for (Estudiante e : servicioEstudiante.getEstudiantes(curso)){
            System.out.println("- " + e.getNombre());
        }
    }
    private void listarNotas() {
        Curso curso = seleccionarCurso();
        if(curso == null)return;
        for (Estudiante e : servicioEstudiante.getEstudiantes(curso)){
            String nota = (e.getNota() != null) ? e.getNota().getValor() : "Sin nota";
            System.out.println(e.getNombre() + ": " + nota);
        }
    }
    private void guardar() {
        try {
            iServicioPersistencia.guardarCursos(servicioCurso.getCursos());
            System.out.println("Datos guardados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar los datos." + e.getMessage());
        }
    }
    private void cargar() {
        try {
            List<Curso> cargados = iServicioPersistencia.cargarCursos();
            servicioCurso.getCursos().clear();
            servicioCurso.getCursos().addAll(cargados);
            System.out.println("Datos cargados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar los datos." + e.getMessage());
        }
    }

    private void mostrarContenidoArchivo(){
        if(iServicioPersistencia instanceof ServicioPersistencia){
            ((ServicioPersistencia) iServicioPersistencia).imprimirArchivo();
        }
        else{
            System.out.println("La persistencia usada no soporta impresión de un archivo de texto plano");
        }

    }
    private Curso seleccionarCurso() {
        listarCursos();
        System.out.println("Seleccione el número de un curso: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            List<Curso> listaCursos = servicioCurso.getCursos();
            if (idx >= 0 && idx < listaCursos.size()) return listaCursos.get(idx);
        } catch (Exception e) {}
            System.out.println("Selección inválida.");
            return null;
    }
    private Estudiante seleccionarEstudiante(Curso curso) {
        List<Estudiante> listaEstudiantes = servicioEstudiante.getEstudiantes(curso);
        int i = 1;
        for (Estudiante e : listaEstudiantes) System.out.println(i++ + ". " + e.getNombre());
        System.out.println("Seleccione el número de un estudiante: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx >= 0 && idx < listaEstudiantes.size()) return listaEstudiantes.get(idx);
        } catch (Exception e) {}
        System.out.println("Selección inválida.");
        return null;
    }



}
