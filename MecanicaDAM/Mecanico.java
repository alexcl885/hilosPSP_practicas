import java.util.Random;

public class Mecanico extends Thread {
    Random random = new Random();
    private int id;
    private Almacen almacen;
    private boolean mecanico;
    private int contador;

    public Mecanico(int id, Almacen almacen, boolean mecanico) {
        this.id = id;
        this.almacen = almacen;
        this.mecanico = mecanico;
    }

    @Override
    public void run() {
        while (!almacen.isCerrado()) {
            if (!almacen.disponible()) {
                System.out.println("El almacén se ha quedado sin piezas. Mecanico " + this.id + " se detiene.");
                break;
            }
            int numero_piezas = random.nextInt(40, 50);
            if (almacen.suministros(numero_piezas)) {
                try {
                    Thread.sleep(random.nextInt(4000, 9000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                almacen.reparar(numero_piezas);
                contador++;
                System.out
                        .println("Soy el mecanico " + this.id + " y estoy reparando con " + numero_piezas + " piezas");
                System.out.println("ID:" + this.id + " NUMERO_realizados:" + contador);
            } else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}