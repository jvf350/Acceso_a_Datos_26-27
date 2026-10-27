package regex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * Validador de datos con expresiones regulares.
 * Lee alumnos.txt (líneas NIF;correo;fecha) y separa las líneas válidas de las
 * incorrectas. Las válidas se guardan en validas.txt.
 */
public class ValidadorAlumnos {

    private static final String FICHERO_ALUMNOS = "alumnos.txt";
    private static final String FICHERO_VALIDAS = "validas.txt";

    // Un patrón por campo
    static final String PATRON_NIF = "\\d{8}-[A-Z]";                      // 8 cifras, guion y letra
    static final String PATRON_CORREO = "[\\w.]+@[\\w.]+\\.[a-z]{2,}";
    // Día 01-31 y mes 01-12 (no sabe cuántos días tiene cada mes)
    static final String PATRON_FECHA = "(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}";

    public static void main(String[] args) {

        if (!new File(FICHERO_ALUMNOS).exists()) {
            crearFicheroDePrueba();
        }

        int numeroLinea = 0;
        int lineasRechazadas = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(FICHERO_ALUMNOS, StandardCharsets.UTF_8));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(FICHERO_VALIDAS, StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                String error = obtenerError(linea);       // null si la línea es válida

                if (error == null) {
                    System.out.println("Línea " + numeroLinea + ": válida");
                    escritor.write(linea);
                    escritor.newLine();
                } else {
                    System.out.println("Línea " + numeroLinea + ": " + error);
                    lineasRechazadas++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
            return;
        }

        System.out.println("Rechazadas: " + lineasRechazadas);
    }

    // Devuelve null si la línea es correcta o el motivo del primer error
    private static String obtenerError(String linea) {
        String[] campos = linea.split(";");
        if (campos.length != 3) {
            return "formato incorrecto (se esperaban 3 campos)";
        }
        // matches() exige que TODO el campo cumpla el patrón (find() no sirve aquí)
        if (!Pattern.matches(PATRON_NIF, campos[0])) {
            return "NIF incorrecto";
        }
        if (!Pattern.matches(PATRON_CORREO, campos[1])) {
            return "correo incorrecto";
        }
        if (!Pattern.matches(PATRON_FECHA, campos[2])) {
            return "fecha incorrecta";
        }
        return null;
    }

    private static void crearFicheroDePrueba() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FICHERO_ALUMNOS, StandardCharsets.UTF_8))) {
            escritor.write("12345678-Z;ana@mail.com;12/02/2005");     escritor.newLine();  // válida
            escritor.write("1234567-Z;luis@mail.com;03/11/2004");     escritor.newLine();  // NIF incorrecto (7 cifras)
            escritor.write("87654321-A;eva.gil@mail.es;25/12/2003");  escritor.newLine();  // válida
            escritor.write("11223344-B;pedro#mail.com;01/01/2006");   escritor.newLine();  // correo incorrecto
            escritor.write("55667788-C;marta@mail.com;15/13/2005");   escritor.newLine();  // mes 13
            escritor.write("99887766-D;juan@mail.com");               escritor.newLine();  // falta la fecha
        } catch (IOException e) {
            System.err.println("No se pudo crear " + FICHERO_ALUMNOS + ": " + e.getMessage());
        }
    }
}
