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
        while(!recurso.isFin()){//mientras que el atributo sea true no para el bucle , no para
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CREANDO LINGOTE");
            recurso.crearLingote();
        }
        System.out.println("HORNO TERMINADO");
    }

    
}