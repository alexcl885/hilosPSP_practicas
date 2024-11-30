public class AsistenteParking extends Thread{
    private int dni ;
    private Parking parking;


    public AsistenteParking(int dni, Parking parking) {
        this.dni = dni;
        this.parking = parking;
    }

    @Override
    public void run() {
        while (parking.isFin()){

        }
    }
    
}
