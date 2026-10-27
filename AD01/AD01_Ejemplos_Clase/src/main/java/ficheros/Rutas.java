package ficheros;

import java.io.File;
import java.io.IOException;

public class Rutas {

    public static void main(String[] args) throws IOException {

        // Cuatro formas de construir la ruta del fichero notas.txt
        File rutaRelativa  = new File("datos/notas.txt");
        File rutaAbsoluta  = new File("C:\\Users\\jm\\notas.txt");
        File rutaConSeparador  = new File("datos" + File.separator + "notas.txt"); //Portable
        File rutaPadreHijo = new File("datos", "notas.txt");

        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
        System.out.println("SSOO: " + System.getProperty("os.name"));
        System.out.println("Separador del sistema: " + File.separator);
        System.out.println();

        mostrar("Relativa", rutaRelativa);
        mostrar("Absoluto", rutaAbsoluta);
        mostrar("Portable", rutaConSeparador);
        mostrar("PadreHijo", rutaPadreHijo);

        // Crear un objeto File no crea nada en disco: hay que hacerlo a mano
        File carpeta = new File("datos");
        carpeta.mkdir();
        rutaRelativa.createNewFile();
        System.out.println("Después de crearlo, ¿existe? " + rutaRelativa.exists()); // true
    }

    public static void mostrar(String nombre, File f) {
        System.out.println(nombre + ":");
        System.out.println("  Ruta indicada:  " + f.getPath());
        System.out.println("  Ruta absoluta:  " + f.getAbsolutePath());
        System.out.println("  ¿Es absoluta?   " + f.isAbsolute());
        System.out.println("  ¿Existe?        " + f.exists());
        System.out.println();
    }
}
