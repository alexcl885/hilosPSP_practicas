public class TicTacTener {
    private String estado = "tacmarcado"; // Comienza esperando un "Tic"

    // Método sincronizado para Tic
    public synchronized void Tic(boolean corriendo) {
        if (!corriendo) {
            notify(); // Notifica al hilo "Tac" que puede desbloquearse si estaba esperando
            return;
        }

        // Espera activa hasta que el estado sea "tacmarcado"
        while (!estado.equals("tacmarcado")) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprime "Tic", actualiza el estado y notifica
        System.out.print("Tic ");
        estado = "ticmarcado";
        notify(); // Notifica al hilo "Tac"
    }

    // Método sincronizado para Tac
    public synchronized void Tac(boolean corriendo) {
        if (!corriendo) {
            return; // No es necesario notificar porque ningún "Tic" está esperando
        }

        // Espera activa hasta que el estado sea "ticmarcado"
        while (!estado.equals("ticmarcado")) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprime "Tac", actualiza el estado y notifica
        System.out.println("Tac");
        estado = "tacmarcado";
        notify(); // Notifica al hilo "Tic"
    }
}
