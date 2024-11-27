
import java.util.Random;

public class Compartido {
    private int numero = 10;
    private boolean sigue = true;


    public synchronized  int getNumero() {
        return this.numero;
    }

    public synchronized  void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean puedePasar(){
        return sigue;
    }

    public boolean   verSiSepuede(){
        synchronized (this) {
            if (this.numero <= 0){
                sigue = false;
                return false;
            }
            else{   
                sigue = true;
                return true;
            }
        }
    }

    public void sumarNumero(){
        synchronized (this) {
            Random random = new Random();
            int random_number = random.nextInt(3);
            numero -= random_number;
            EstadoDelNumero();
            
        }
    }
    private void EstadoDelNumero(){
        System.out.println("Numero: " +numero);
    }


}