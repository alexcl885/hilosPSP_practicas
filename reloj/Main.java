public class Main {
    public static void main(String[] args) {
        TicTacTener recurso = new TicTacTener();

        HiloTicTac hiloTic = new HiloTicTac(recurso, true); // Hilo Tic
        HiloTicTac hiloTac = new HiloTicTac(recurso, false); // Hilo Tac

        // Iniciar los hilos
        hiloTic.start();
        hiloTac.start();

        // Esperar a que ambos hilos terminen
        try {
            hiloTic.join();
            hiloTac.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Reloj terminado.");
    }
}
