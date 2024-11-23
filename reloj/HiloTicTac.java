public class HiloTicTac extends Thread {
    private final TicTacTener ticTacTener;
    private final boolean esTic;

    public HiloTicTac(TicTacTener ticTacTener, boolean esTic) {
        this.ticTacTener = ticTacTener;
        this.esTic = esTic;
    }

    @Override
    public void run() {
        // Llama al método Tic o Tac 5 veces con `corriendo = true`
        for (int i = 0; i < 5; i++) {
            if (esTic) {
                ticTacTener.Tic(true);
            } else {
                ticTacTener.Tac(true);
            }
        }

        // Llama una última vez con `corriendo = false` para desbloquear al otro hilo
        if (esTic) {
            ticTacTener.Tic(false);
        } else {
            ticTacTener.Tac(false);
        }
    }
}
