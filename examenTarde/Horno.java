/**
 * Hilo Horno
 */
public class Horno extends Thread {
    private int horno;
    private RecursoFunducion recurso;


    public Horno(int horno, RecursoFunducion recurso) {
        this.horno = horno;
        this.recurso = recurso;
    }


    @Override
    public void run() {
        while(!recurso.terminaPrograma()){//mientras que el atributo sea true no para el bucle , no para
            recurso.crearLingote();
        }
    }

    
}