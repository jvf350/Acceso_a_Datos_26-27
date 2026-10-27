package ficheros;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * L/E ficheros de acceso aleatorio
 * Tamaño de los registros:
 *  Campo   tipo    Bytes
 *  id      int     4
 *  nombre  14 char 28
 *  nota    double  8
 */

public class AccesoAleatorio1 {

    static final int TAM_REGISTRO = 40;   // 4 + 14 * 2 + 8
    static final int LONG_NOMBRE = 14;

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile("registros.dat", "rw")) {

            raf.setLength(0);   //vacía el fichero si ya existía

            // Escritura 10 registros
            for (int i = 0; i < 10; i++) {
                raf.writeInt(i);
                raf.writeChars(ajustar("json.Alumno" + i, LONG_NOMBRE));
                raf.writeDouble(5 + i * 0.5);
            }

            long totalRegistros = raf.length() / TAM_REGISTRO;
            System.out.println("Registros: " + totalRegistros);          // 10

            // Saltar directamente al registro 7
            /*
            int numRegistro = 7;
            long posicion = (long) numRegistro * TAM_REGISTRO;           // 280
            raf.seek(posicion);

            int id = raf.readInt();
            String nombre = leerCadena(raf, LONG_NOMBRE);
            double nota = raf.readDouble();
            System.out.println("Byte " + posicion + ": " + id + " " + nombre + " " + nota);
            */

            //Imprimimos todos los registros
            for (int i= 0; i<raf.length();i++) {
                long posicion = (long) i * TAM_REGISTRO;
                raf.seek(posicion);

                int id = raf.readInt();
                String nombre = leerCadena(raf, LONG_NOMBRE);
                double nota = raf.readDouble();
                System.out.println("Byte " + posicion + ": " + id + " " + nombre + " " + nota);
            }
        }
    }

    // Rellena con espacios (o recorta) hasta n caracteres
    static String ajustar(String s, int n) {
        return String.format("%-" + n + "s", s).substring(0, n);
    }

    // Lee n caracteres y quita los espacios del relleno
    static String leerCadena(RandomAccessFile raf, int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(raf.readChar());
        }

        return sb.toString().trim();
    }
}
