package ficheros;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LeerFicheroConNIO2 {

    public static void main(String[] args) throws IOException {

        // Cada apertura sobrescribe el fichero
        try (FileWriter fw = new FileWriter("demo.txt")) {
            fw.write("Primera linea\n");
        }
        try (FileWriter fw = new FileWriter("demo.txt")) {
            fw.write("Segunda línea\n");
        }
        //System.out.println("1:\n" + Files.readString(Path.of("demo.txt")));
        try (FileReader fr = new FileReader("demo.txt")) {
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
        }

        // Abrimos fichero en modo append (segundo argumento true): se añade al final
        try (FileWriter fw = new FileWriter("demo.txt", true)) {
            fw.write("Tercera linea\n");
        }

        //Lectura con NIO
        //System.out.println("2:\n" + Files.readString(Path.of("demo.txt")));

        //Lectura con FileReader
        try (FileReader fr = new FileReader("demo.txt", StandardCharsets.UTF_8)) {
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}
