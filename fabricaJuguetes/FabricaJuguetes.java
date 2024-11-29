
import java.util.ArrayList;
import java.util.List;

/**
 * CLASE PRINCIPAL DONDE EJECUTARA EL PROGRAMA
 */
public class FabricaJuguetes {
    public static void main(String[] args) {
        List<Trabajador> trabajadores = new ArrayList<>();
        Almacen almacen = new Almacen();
        Supervisor supervisor = new Supervisor("TUVERAS");
        for (int i = 0; i < 10; i++) {
            Trabajador trabajador = new Trabajador(i, almacen);
            trabajador.start();
        }
        Asistente asistente = new Asistente("Miguelon", almacen, supervisor);
        asistente.start();

        for (Trabajador trabajador : trabajadores) {
            try {
                trabajador.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            asistente.join();
            supervisor.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("###  PROGRAMA TERMINADO  ###");
    }
}