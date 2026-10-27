package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Ejemplo básico de JSON con org.json: construir un JSON, guardarlo en un
 * fichero, leerlo de nuevo y recorrer sus valores.
 */
public class EscrituraLecturaJson {

    private static final String FICHERO_JSON = "alumno.json";

    public static void main(String[] args) throws IOException {
        escribirJson();
        leerJson();
    }

    // Objeto Java -> JSON -> fichero
    public static void escribirJson() throws IOException {
        JSONObject alumno = new JSONObject();
        alumno.put("nombre", "Ana");
        alumno.put("edad", 20);
        alumno.put("activo", true);
        alumno.put("tutor", JSONObject.NULL);        // la clave se guarda con valor null

        JSONArray notas = new JSONArray();           // lista de valores
        notas.put(7.5);
        notas.put(8.0);
        notas.put(6.25);
        alumno.put("notas", notas);

        // toString(4) genera el texto con sangría de 4 espacios
        try (FileWriter escritor = new FileWriter(FICHERO_JSON, StandardCharsets.UTF_8)) {
            escritor.write(alumno.toString(4));
        }
        System.out.println("Fichero escrito:\n" + alumno.toString(4));
    }

    // Fichero -> String -> JSONObject -> atributos
    public static void leerJson() throws IOException {
        String contenido = Files.readString(Path.of(FICHERO_JSON), StandardCharsets.UTF_8);
        JSONObject alumno = new JSONObject(contenido);

        String nombre = alumno.getString("nombre");
        int edad = alumno.getInt("edad");
        boolean activo = alumno.getBoolean("activo");
        System.out.println("\nLeído: " + nombre + ", " + edad + " años, activo = " + activo);

        // Recorrer un array
        JSONArray notas = alumno.getJSONArray("notas");
        double suma = 0;
        for (int i = 0; i < notas.length(); i++) {
            double nota = notas.getDouble(i);
            System.out.println("Nota " + (i + 1) + ": " + nota);
            suma += nota;
        }
        System.out.println("Media: " + suma / notas.length());

        comprobarClaves(alumno);
    }

    // Claves que faltan o valen null
    public static void comprobarClaves(JSONObject alumno) {
        System.out.println("\n¿Tiene 'nombre'?  " + alumno.has("nombre"));     // true
        System.out.println("¿Tiene 'correo'?  " + alumno.has("correo"));       // false
        System.out.println("¿'tutor' es null? " + alumno.isNull("tutor"));     // true
        System.out.println("optString correo: " + alumno.optString("correo", "(sin correo)"));

        try {
            alumno.getString("correo");              // la clave no existe
        } catch (JSONException e) {
            System.err.println("getString lanza JSONException: " + e.getMessage());
        }
    }
}
