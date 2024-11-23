import java.util.Random;

/*PRODUCTOR */ public class ResponsableTaller extends Thread {
    private int id;
    private Almacen almacen;
    private boolean mecanico;
    Random random = new Random();

    public ResponsableTaller(int id, Almacen almacen, boolean mecanico) {
        this.id = id;
        this.almacen = almacen;
        this.mecanico = mecanico;
    }

    @Override
    public void run() {
        while (!almacen.isCerrado()) {
            if (!almacen.disponible()) {
                System.out
                        .println("El almacén se ha quedado sin piezas. ResponsableTaller " + this.id + " se detiene.");
                break;
            }
            try {
                Thread.sleep(random.nextInt(6000, 12000));
                if (!almacen.isCerrado()) {
                    System.out.println("SOY EL RESPONSABLE Y HE INTRODUCIDO PIEZAS");
                    almacen.introducirPiezas();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}