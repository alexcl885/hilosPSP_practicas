/**
 * Hilo persona
 */
public class Persona extends Thread {
    private String nombre;
    private RecursoFunducion recurso;

    public Persona(String nombre, RecursoFunducion recurso) {
        this.nombre = nombre;
        this.recurso = recurso;
    }

    @Override
    public void run() {
        while (!recurso.isFin()) {
            try {
                recurso.consumirLingote(nombre);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha terminado");
    }

}