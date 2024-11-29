public class Supervisor extends Thread {
    private  String nombre;

    public Supervisor(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            System.out.println("SOY EL SUPERVISOR " + this.nombre + " Y VOY A ENTREGAR LOS JUGUETES JEJEJEJE"); 
        }
    }




    
}