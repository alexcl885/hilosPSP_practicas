import java.util.ArrayList;
import java.util.List;

/**
 * Esta sera mi clase main donde se ejecutara mi programa entero
 * creare los hilos requeridos los lanzare y esperare hasta que se terminen 
 * de ejecutar
 */
public class PreparandoPlatos {
    public static void main(String[] args) {
        List<Cocinero> cocineros = new ArrayList<>();
        
        Depositos depositos = new Depositos(); //creamos el recurso compartido

        for (int i = 0; i < 3; i++) { //preparo 3 cocineros que preparen con agua fria
            Cocinero cocinero_frios = new Cocinero(i, depositos, true);
            cocinero_frios.start(); //los inicio
            cocineros.add(cocinero_frios); //lo meto en una lista para poder hacer el join de cada uno
        }
        for (int i = 3; i < 6; i++) {//preparo a 3 cocineros que preparen con agua caliente
            Cocinero cocinero_calientes = new Cocinero(i, depositos, true);
            cocinero_calientes.start(); //los inicio
            cocineros.add(cocinero_calientes);//lo meto en una lista para poder hacer el join de cada uno
        }

        Temporalizador temporalizador = new Temporalizador(1, depositos);
        temporalizador.start();

        for (Cocinero cocinero : cocineros) {
            try {
                cocinero.join();
            } catch (InterruptedException e) {
               
                e.printStackTrace();
            }
        }
        try {
            temporalizador.join();
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }

        //mensaje que nos requiere el ejercicio
        System.out.println("Desde Masterchef se han realizado -> "+depositos.getCANTIDAD_PLATOS() +" PLATOS");
        System.out.println("##  PROGRAMA TERMINADO  ##");


    }
}
