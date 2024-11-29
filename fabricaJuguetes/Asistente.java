public class Asistente extends Thread {
    private String nombre;
    private Almacen almacen;
    private Supervisor supervisor;

    public Asistente(String nombre, Almacen almacen, Supervisor supervisor) {
        this.nombre = nombre;
        this.almacen = almacen;
        this.supervisor = supervisor;
    }

    @Override
    public void run() {
        while (!almacen.isFin()){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            almacen.rellenarPegamento();
        }
        System.out.println("ASISTENTE FINALIZADO");
        supervisor.interrupt();
    }
    
}
