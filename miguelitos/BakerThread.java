import java.util.Random;

/*
 * Los pasteleros, sólo tienen que hacer miguelitos
 * mientras haya recursos.
 * 
 * Sólo utilizan los métodos de la clase safe.
 */
public class BakerThread extends Thread {
    private String name;
    private int id;
    private BakeryLaRoda bakery;
    private int numMiguelitos;
    

    public BakerThread(int _id, String _name, BakeryLaRoda bk){
        name = _name; id = _id; bakery = bk; numMiguelitos = 0;
    }


    @Override
    public void run() {
        Random r1 = new Random(); //Utilizaré dos semillas diferentes.
        Random r2 = new Random();

        //Mientras hayan recursos para ese día.
        while (!bakery.isFin()){  //mientras exista harina y/o agua

            //OBTENEMOS TIEMPOS ALEATORIOS PARA EL AGUA
            int cWater = Parameters.CANT_MIN_WATER_MIGUELITO + 
                r1.nextInt(
                    Parameters.CANT_MAX_WATER_MIGUELITO - Parameters.CANT_MIN_WATER_MIGUELITO
                ) +
                1; 

            //Aleatorios para la harina.
            int cFlour = Parameters.CANT_MIN_FLOUT_MIGUELITO + 
                r2.nextInt(
                    Parameters.CANT_MAX_FLOUT_MIGUELITO - Parameters.CANT_MIN_FLOUT_MIGUELITO
                ) +
                1;

            /*
             * Cuando alguno compruebe que no hay agua o harina.
             * Informa de que ya ha llegado el fin de la jornada.
             */

            if (!bakery.restCantWater(cWater) || !bakery.restCantFlour(cFlour) )
                bakery.setFin(true);  //debemos de parar, ya que hay harina o agua que se ha terminado.
            else{
                
                /*
                 * Si tiene agua y harina, intenta sacar el azucar que le haga falta.
                 */

                 //Tiempo aleatorio en hacer el miguelito.
                int tMake = Parameters.TIME_MIN_MAKE_MIGUELITO + 
                    r1.nextInt(
                        Parameters.TIME_MAX_MAKE_MIGUELITO - Parameters.TIME_MIN_MAKE_MIGUELITO
                    ) +
                    1;

                //Cantidad aleatoria de azucar para ese miguelito.
                int cSugar = Parameters.CANT_MIN_SUGAR_MIGUELITO + 
                    r2.nextInt(
                        Parameters.CANT_MAX_SUGAR_MIGUELITO - Parameters.CANT_MIN_SUGAR_MIGUELITO
                    ) +
                    1;

                /*
                 * Esta es la clave. Se bloqueará siempre que no haya azucar. Cuando
                 * tenga azucar suficiente, simplemente hace el miguelito, informa y
                 * notifica. En caso contrario, debe esperar al asistente a que compre
                 * el azucar.
                 */
                
                try {
                    System.out.printf("Soy el pastelero de nombre %s y voy a hacer un miguelito. He cogido %d de agua y %d de harina, Me falta el azucar\n",
                            name, cWater, cFlour);
                    bakery.getCantSugar(cSugar);    //requerimos azucar (BLOQUEO CUANDO NO HAYA AZUCAR)
                    Thread.sleep(tMake);                   //tiempo que emplea un empleado a hacer un miguelito  
                    numMiguelitos++;                //incrementamos el número de miguelitos de ese pastelero.
                    bakery.incNumMiguelitos();      //incrementamos el número de miguelitos que llevan todos.
                    System.out.printf("-------Pastelero de nombre %s, acabo de obtener azucar para miguelido. Ya llevo %d Miguelitos. Queda de agua %d, de harina %d, y de azucar %d\n",
                     name, numMiguelitos, bakery.getWater(), bakery.getFlour(), bakery.getSugar());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } //fin else

        }
        System.out.printf("Soy el pastelero de nombre %s con id %d y He FINALIZADO con  %d miguelitos\n", name, id, numMiguelitos);

    }
    
}
