package json;

import org.json.JSONObject;

/**
 * json.Alumno con nombre, edad, nota y un tutor opcional (puede ser null).
 * Se usa en json.AlumnosJson (JSON).
 */
public class Alumno {

    private final String nombre;
    private final int edad;
    private final double nota;
    private final String tutor;

    //Constructor con 3 parámetros
    public Alumno(String nombre, int edad, double nota) {
        this(nombre, edad, nota, null);
    }

    //Constructor con 4 parámetros
    public Alumno(String nombre, int edad, double nota, String tutor) {
        this.nombre = nombre;
        this.edad = edad;
        this.nota = nota;
        this.tutor = tutor;
    }

    public String getNombre() { return nombre; }
    public int getEdad()      { return edad; }
    public double getNota()   { return nota; }
    public String getTutor()  { return tutor; }

    // Objeto Java -> JSON. put(clave, valor) añade cada atributo según su tipo
    public JSONObject getAsJSON() {
        JSONObject json = new JSONObject();
        json.put("nombre", nombre);
        json.put("edad", edad);
        json.put("nota", nota);
        // Si el tutor es null, JSONObject.NULL guarda la clave con valor null
        // (si se hiciera put("tutor", null), la clave NO se guardaría)

        //json.put("tutor", tutor != null ? tutor : JSONObject.NULL);

        if (tutor != null) {
            json.put("tutor", tutor);
        } else {
            json.put("tutor", JSONObject.NULL);
        }
        return json;
    }

    // JSON -> objeto Java. Es el camino inverso a getAsJSON()
    public static Alumno fromJSON(JSONObject json) {
        String nombre = json.getString("nombre");   // lanza JSONException si falta la clave
        int edad = json.getInt("edad");
        double nota = json.getDouble("nota");
        // Campo opcional: optString devuelve el valor por defecto (null) si la clave
        // no existe o vale null
        String tutor = json.optString("tutor", null);
        return new Alumno(nombre, edad, nota, tutor);
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + "): " + nota;
    }
}
