
import java.util.ArrayList;

public class MecanicaDAM {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        //creo los cuatro mecanicos
        ArrayList<Mecanico> mecanicos = new ArrayList<>();
        for (int i=0; i< 3; i++){
            Mecanico mecanico = new Mecanico(i, almacen, true);
            mecanicos.add(mecanico);
            mecanico.start();
        }
        
        ResponsableTaller responsableTaller = new ResponsableTaller(1, almacen, false);
        responsableTaller.start();

        for (Mecanico mecanico : mecanicos) {
            try {
                mecanico.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            responsableTaller.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("###PROGRAMA TERMINADO");

    }
}
