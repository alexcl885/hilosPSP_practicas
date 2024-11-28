/*
 * El asistente, tendrá que ir periódicamente a comprar
 * azucar. Para simularlo, simplemente lo dormimos. Cuando despierte,
 * si no han finalizado todos los pasteleros, tendrá que ir a comprar azucar.
 */
public class AssistantBaker extends Thread{

    private String name;
    private BakeryLaRoda bakery;  //recurso compartido
    private OunerThread ounerThread; //Necesita una referencia del jefe, para interrumpirlo.

    public AssistantBaker(String _name, BakeryLaRoda bk, OunerThread _ounerThread){
        name = _name; bakery = bk; ounerThread = _ounerThread;
    }

    @Override
    public void run(){
        while (! bakery.isFin()) {  //mientras exista harina o agua
            try {
                Thread.sleep(Parameters.TIME_BUY_SUGAR);       //tiempo que tardo en comprar.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //después de despertarse, compra azucar
            bakery.setCantSugar(Parameters.CANT_BUY_SUGAR); //ya he comprado
            System.out.printf("======soy %s y acabo de comprar azucar. Hay %d de azucar, %d de agua, %d de harina\n",
                name,bakery.getSugar(), bakery.getWater(), bakery.getFlour());
        }//fin while

        //Cuando acaban todos de hacer miguelitos, debe de avisar al jefe.
       // bakery.setCantSugar(0); //por si hubiera alguno bloqueado.
        System.out.println("Una vez terminados de hacer los Miguelitos por hoy, voy a informar a mi jefe");
        ounerThread.interrupt();
    }
    
}
