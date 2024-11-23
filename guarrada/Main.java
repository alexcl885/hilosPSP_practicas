public class Main {
    public static void main(String[] args) {
        Compartido compartido = new Compartido();
        Hilo uno = new Hilo("UNO", compartido);
        Hilo dos = new Hilo("DOS", compartido);

        uno.start();
        dos.start();

        uno.interrupt();
        dos.interrupt();

        try {
            uno.join();
            dos.join();
        } catch (InterruptedException ex) {
            System.out.println("Error-> " + ex.getLocalizedMessage());
        }
    }
}