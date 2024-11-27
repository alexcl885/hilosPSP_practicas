/**
 * Hilo persona
 */
public class Persona extends Thread{
    private  String nombre;
    private RecursoFunducion recurso;


    public Persona(String nombre, RecursoFunducion recurso) {
        this.nombre = nombre;
        this.recurso = recurso;
    }
    @Override
    public void run() {
        while(!recurso.terminaPrograma()){
            if (!recurso.finish( nombre)){
                recurso.consumirLingote();
            }
            
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        
    }
    
}