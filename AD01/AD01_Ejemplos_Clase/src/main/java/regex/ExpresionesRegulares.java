package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ejemplo de expresiones regulares: contiene (find) o coincide (matches),
 * Pattern, Matcher, grupos, split y replaceAll.
 */
public class ExpresionesRegulares {

    // En Java, cada \ del patrón se escribe doble
    private static final String PATRON_NIF = "\\d{8}-[A-Z]";

    public static void main(String[] args) {
        contieneOCoincide();
        validarDatosCompletos();
        buscarTodasLasCoincidencias();
        extraerGrupos();
        dividirYReemplazar();
    }

    private static void contieneOCoincide() {
        Pattern patronNif = Pattern.compile(PATRON_NIF);
        String frase = "Mi NIF es 12345678-Z";

        System.out.println("find (contiene):    " + patronNif.matcher(frase).find());            // true
        System.out.println("matches (coincide): " + patronNif.matcher(frase).matches());         // false
        System.out.println("NIF solo:           " + patronNif.matcher("12345678-Z").matches());  // true
    }

    // Pattern.matches comprueba que TODO el texto cumple el patrón
    private static void validarDatosCompletos() {
        System.out.println("\nCódigo postal 28013: " + Pattern.matches("\\d{5}", "28013"));              // true
        System.out.println("Móvil 612345678:    " + Pattern.matches("[67]\\d{8}", "612345678"));       // true
        System.out.println("Móvil 912345678:    " + Pattern.matches("[67]\\d{8}", "912345678"));       // false
        System.out.println("Correo ana@mail.com: " + Pattern.matches("[\\w.]+@[\\w.]+\\.[a-z]{2,}", "ana@mail.com")); // true
        System.out.println("Fecha 31/02/2025:   " + Pattern.matches("\\d{2}/\\d{2}/\\d{4}", "31/02/2025")); // true
    }

    private static void buscarTodasLasCoincidencias() {
        String texto = "Mi NIF es 12345678-Z y el tuyo 87654321-A";
        Matcher buscador = Pattern.compile(PATRON_NIF).matcher(texto);

        System.out.println();
        while (buscador.find()) {
            // group() devuelve la parte encontrada; start() y end() su posición
            System.out.println("Encontrado: " + buscador.group()
                    + " (posiciones " + buscador.start() + "-" + buscador.end() + ")");
        }
    }

    // Los paréntesis crean grupos: group(1), group(2)...
    private static void extraerGrupos() {
        Pattern patronFecha = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
        Matcher buscador = patronFecha.matcher("Hoy es 12/02/2025");

        if (buscador.find()) {
            System.out.println("\nFecha completa: " + buscador.group());   // 12/02/2025
            System.out.println("Día:  " + buscador.group(1));              // 12
            System.out.println("Mes:  " + buscador.group(2));              // 02
            System.out.println("Año:  " + buscador.group(3));              // 2025
        }
    }

    private static void dividirYReemplazar() {
        String linea = "12345678-Z;ana@mail.com;12/02/2005";
        String[] campos = linea.split(";");
        System.out.println("\nCampos: " + campos.length + " -> " + campos[1]);
        System.out.println("Dígitos ocultos: " + linea.replaceAll("\\d", "#"));
    }
}
