package ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class LeerFicheroCaracterACaracter {

    public static class Main {

        public static void main(String[] args) {
            // Creamos el directorio con ruta relativa
            File directorio = new File("directorio");
            if (!directorio.exists()) {
                directorio.mkdir();
            }

            // Creamos la referencia al fichero dentro del directorio
            File fichero = new File(directorio, "fichero.txt");

            // Escribimos el contenido manualmente mediante FileWriter
            try (FileWriter escritor = new FileWriter(fichero)) {
                escritor.write("Hola mundo");
            } catch (IOException e) {
                System.err.println("Error al escribir en el fichero: " + e.getMessage());
            }

            // Leemos carácter a carácter con FileReader.read()
            try (FileReader lector = new FileReader(fichero)) {
                int caracter;
                // read() devuelve el int del carácter o -1 al llegar al final
                while ((caracter = lector.read()) != -1) {
                    System.out.println(caracter);
                }
            } catch (IOException e) {
                System.err.println("Error al leer el fichero: " + e.getMessage());
            }
        }
    }
}