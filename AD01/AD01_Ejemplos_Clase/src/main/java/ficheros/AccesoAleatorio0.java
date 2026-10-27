package ficheros;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoAleatorio0 {

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile("notas.dat", "rw")) {
            raf.setLength(0);                 // vacía el fichero si ya existía

            raf.writeInt(1);                  // id: 4 bytes
            raf.writeChars("Ana       ");     // 10 caracteres: 20 bytes
            raf.writeDouble(7.5);             // nota: 8 bytes
            System.out.println("Tamaño del registro: " + raf.length() + " bytes"); // 32

            raf.seek(0);                      // volver al inicio
            int id = raf.readInt();
            StringBuilder nombre = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                nombre.append(raf.readChar());
            }
            double nota = raf.readDouble();

            System.out.println(id + " " + nombre.toString().trim() + " " + nota);
        }
    }
}
