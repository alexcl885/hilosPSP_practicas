public class PartidoPINGPONG {
    public static void main(String[] args) {
        Partida partida = new Partida();
        HiloZipZap hiloSanti = new HiloZipZap("Santi", partida, true);
        HiloZipZap hiloCopado = new HiloZipZap("Copado", partida, false);

        hiloSanti.start();
        hiloCopado.start();

        try {
            hiloSanti.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            hiloCopado.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("###JUEGO TERMINADO");
    }
}
