package ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LeerFicheroTextoFlujo {

    public static void main(String[] args) {

        // Lee un fichero byte a byte hasta el final (-1)
        try (FileInputStream in = new FileInputStream("demo.txt")) {
            int dato;
            int total = 0;
            while ((dato = in.read()) != -1) {
                System.out.print((char) dato);
                total++;
            }
            System.out.println("\nBytes leídos: " + total);
        } catch (FileNotFoundException e) {
            System.out.println("No existe el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }

    }


}
