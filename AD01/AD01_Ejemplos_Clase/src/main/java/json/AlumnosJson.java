package json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo para guardar y recuperar una lista de alumnos en un fichero JSON
 * con org.json, sin perder ningún dato. Usa la clase json.Alumno.
 */
public class AlumnosJson {

    private static final Path FICHERO_JSON = Path.of("alumnos.json");

    public static void main(String[] args) throws IOException {

        // Luis no tiene tutor: es la ampliación con valor null
        List<Alumno> alumnos = List.of(
                new Alumno("Ana", 20, 7.5, "Marta"),
                new Alumno("Luis", 21, 6.0),
                new Alumno("Eva", 19, 9.0, "Pedro"));

        guardarAlumnosEnJson(alumnos);
        List<Alumno> alumnosLeidos = cargarAlumnosDeJson();

        mostrarAlumnos(alumnosLeidos);
        System.out.println("Media: " + calcularNotaMedia(alumnosLeidos));
        mostrarTutores(alumnosLeidos);
    }

    // Lista de json.Alumno -> JSONArray -> fichero
    private static void guardarAlumnosEnJson(List<Alumno> alumnos) throws IOException {
        JSONArray array = new JSONArray();
        for (Alumno alumno : alumnos) {
            array.put(alumno.getAsJSON());
        }
        Files.writeString(FICHERO_JSON, array.toString(4), StandardCharsets.UTF_8);
    }

    // Fichero -> String -> JSONArray -> lista de json.Alumno
    private static List<Alumno> cargarAlumnosDeJson() throws IOException {
        String contenido = Files.readString(FICHERO_JSON, StandardCharsets.UTF_8);
        JSONArray array = new JSONArray(contenido);

        List<Alumno> alumnos = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject objetoAlumno = array.getJSONObject(i);
            alumnos.add(Alumno.fromJSON(objetoAlumno));
        }
        return alumnos;
    }

    private static void mostrarAlumnos(List<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    private static double calcularNotaMedia(List<Alumno> alumnos) {
        double suma = 0;
        for (Alumno alumno : alumnos) {
            suma += alumno.getNota();
        }
        return suma / alumnos.size();
    }

    // Ampliación: el tutor puede ser null
    private static void mostrarTutores(List<Alumno> alumnos) {
        System.out.println("\n--- Tutores ---");
        for (Alumno alumno : alumnos) {
            String tutor = alumno.getTutor() != null ? alumno.getTutor() : "(sin tutor)";
            System.out.println(alumno.getNombre() + " -> " + tutor);
        }
    }
}
