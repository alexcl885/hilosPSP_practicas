public class PasteleriaAlbacete {

    public static void main(String[] args) {

        //recurso compartido
        BakeryLaRoda bakery = new BakeryLaRoda(
            Parameters.MAX_DEPOSIT_SUGAR, 
            Parameters.MAX_DEPOSIT_WATER, 
            Parameters.MAX_DEPOSIT_FLOUT);

        //Tenemos nuestros pasteleros
        BakerThread [] bakers  = new BakerThread [Parameters.MAX_BAKER];  
        
        //Yo soy el jefe
        OunerThread ouner = new OunerThread("Santi");  //podríamos tb, pasarle el rec. compartido
        
        AssistantBaker assistan = new AssistantBaker("Pedro Sanchez", bakery, ouner);
        
        //Creamos los pasteleros y los lanzamos.
        for (int i=0; i<Parameters.MAX_BAKER; i++){
            bakers[i] = new BakerThread(i, "Past " + i, bakery);
            bakers[i].start();
        }

        assistan.start();        //lanzamos el que compra azucar.
        ouner.start();             //lanzamos al jefe.
        
        
        try{ 
            for (BakerThread b: bakers)
                b.join();  //esperamos a que finalicen todos.
            
            ouner.join();   //esperamos al jefe. 
        }
        catch(InterruptedException e){};

        //Informamos
        System.out.println("///////DESDE EL PPAL, EL TOTAL DE MIGUELITOS, ES DE " + bakery.getNumMiguelitos()+", AHORA FALTA LA SIDRA....///////");
       

    } //fin main
    
}
