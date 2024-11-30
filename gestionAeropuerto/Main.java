import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Coche> coche_normales = new ArrayList<>();
        List<Coche> coche_electricos = new ArrayList<>();
        
        Parking parking = new Parking(); //nos creamos el recurso compartido

        for (int i = 0; i < 10; i++) {
            Coche coche_normal = new Coche(i,parking, true);
            coche_normal.start();
            coche_normales.add(coche_normal);
        }
        for (int i = 10; i < 20; i++) {
            Coche coche_electrico = new Coche(i, parking, false);
            coche_electrico.start();
            coche_electricos.add(coche_electrico);
        }

        //para esperar que los hilos terminen de ejecutarse
        for (Coche normal : coche_normales) {
            try {
                normal.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        for (Coche electricos : coche_electricos) {
            try {
                electricos.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("##PARKING CERRADO");
    }

}
