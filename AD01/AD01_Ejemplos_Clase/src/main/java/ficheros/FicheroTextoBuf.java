package ficheros;

import java.io.*;

/**
 * L/E Fichero texto con buffer
 */

public class FicheroTextoBuf {

    private static final String RUTA_RELATIVA = "src/main/java/ficheros/FicheroTexto1.txt";

    public static void main(String[] args) {
        if (crearCarpeta()) {
            escribir();
            leer();
        }
    }

    // ---------- CREAR CARPETA ----------
    private static boolean crearCarpeta() {
        File carpeta = new File(RUTA_RELATIVA).getParentFile();   // "src/main/java/ficheros"

        if (carpeta != null && !carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear la carpeta: " + carpeta.getAbsolutePath());
                return false;
            }
        }
        return true;
    }

    // ---------- ESCRITURA ----------
    private static void escribir() {

        BufferedWriter fichero = null;

        try {
            fichero = new BufferedWriter(new FileWriter(RUTA_RELATIVA));
            for (int i = 1; i < 11; i++) {
                fichero.write("Fila numero:" + i); // escribe una línea
                fichero.newLine();                  // salto de línea
            }
            System.out.println("Fichero escrito correctamente");
        } catch (FileNotFoundException fn) {
            System.err.println("No se encuentra el fichero: " + fn.getMessage());
        } catch (IOException io) {
            System.err.println("Error de E/S al escribir: " + io.getMessage());
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();                // vuelca el buffer y cierra
                }
            } catch (IOException ex) {
                System.err.println("Error al cerrar el fichero: " + ex.getMessage());
            }
        }
    }

    // ---------- LECTURA ----------
    private static void leer() {

        try (BufferedReader fichero = new BufferedReader(new FileReader(RUTA_RELATIVA))) {
            String linea;
            while ((linea = fichero.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException fn) {
            System.err.println("No se encuentra el fichero: " + fn.getMessage());
        } catch (IOException io) {
            System.err.println("Error de E/S al leer: " + io.getMessage());
        }
    }
}