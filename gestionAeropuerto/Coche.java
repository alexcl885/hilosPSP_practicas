
import java.util.Random;

public class Coche extends Thread {
    private int matricula;
    private Parking parking;
    private boolean normal; // distinguir los tipos de coches normales o electricos

    Random random = new Random();

    public Coche(int matricula, Parking parking, boolean normal) {
        this.matricula = matricula;
        this.parking = parking;
        this.normal = normal;
    }

    @Override
    public void run() {
        while (!parking.isFin()) {
            if (!parking.parkingCompleto()) {
                parking.setFin(true); // para terminar el programa
            } else { // desarrollamos el parking

                if (normal) { // si es un coche normal
                    while (!parking.entrarZonaNormal()) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Soy el coche N " + this.matricula + "y estoy en el parking");
                    try {
                        Thread.sleep(random.nextInt(1000, 3000));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    parking.salirZonaNormal();

                } else { // si es un coche electrico
                    while (!parking.entrarZonaElectricos()) {
                        try {
                            Thread.sleep(random.nextInt(2000, 3000));
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Soy el coche E " + this.matricula + "y estoy en el parking");
                    try {
                        Thread.sleep(random.nextInt(1000, 8000));
                    } catch (InterruptedException e) {
                     
                        e.printStackTrace();
                    } 
                    System.out.println("COCHE "+ this.matricula + " terninado");
                    parking.salirZonaElectricos();
                }
            }

        }
        

    }

}