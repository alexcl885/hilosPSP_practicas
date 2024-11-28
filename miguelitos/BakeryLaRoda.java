/*
 * Este será mi recurso compartido.
 * Tenemos los depósitos de azucar, agua y harina.
 * Tenemos una variable para que los pasteleros se enteren
 * si ya acaba la jornada laboral. Para ellos, cuando ya no
 * quede agua o harina, significará que tienen que terminar.
 * 
 * Para la sincronización, decido crear un objeto para no tener
 * que bloquear al recurso completo.
 */
public class BakeryLaRoda{
    private int numMiguelitos = 0;
    private int sugar;  //cantidad de azucar que queda.
    private int water;  //depósito de agua que queda.
    private int flour;  //cantidad de harina que queda.
    private Object objetSugar = new Object();  //para la sincronización.
    private boolean fin = false; //para informar del fin de jornada.


   
    public BakeryLaRoda(int _sugar, int _water, int _flour){
        sugar = _sugar; water = _water; flour = _flour;
    }

    //Incrementamos el número de miguelitos totales
    synchronized public void incNumMiguelitos(){
        numMiguelitos++;
    }

    //Leemos el número de miguelitos hechos ese dia.
    synchronized public int getNumMiguelitos() {
        return numMiguelitos;
    }


    //Por si lo necesitara.
    synchronized public void setNumMiguelitos(int numMiguelitos) {
        this.numMiguelitos = numMiguelitos;
    }


    //leemos la cantidad de azucar.
    synchronized public int getSugar() {
        return sugar;
    }


    //seteamos la cantidad de azucar. 
    synchronized public void setSugar(int sugar) {
        this.sugar = sugar;
    }


    //leemos la cantidad de agua.
    synchronized public int getWater() {
        return water;
    }


    //seteamos la cantidad de agua. No es obligatorio sincronizarlo.
    synchronized public void setWater(int water) {
        this.water = water;
    }


    //leemos la cantidad de harina.
    synchronized public int getFlour() {
        return flour;
    }


    //seteamos la cantidad de harina. No es obligatorio sincronizarlo.
    synchronized public void setFlour(int flour) {
        this.flour = flour;
    }


    //comprobamos si los hilos deben acabar.
    synchronized public boolean isFin() {
        return fin;
    }


    //seteamos el final.
    synchronized public void setFin(boolean fin) {
        this.fin = fin;
    }

    //restamos cantidad de harina. Quitaremos harina siempre y cuando tenga suficiente.
    synchronized public boolean restCantFlour(int c){
        if (flour >= c){
            flour -= c;
            return true;
        }
        return false;
    }
    
    //restamos cantidad de agua. Quitaremos agua siempre y cuando tenga suficiente.
    synchronized public boolean restCantWater(int c){
        if (water >= c){
            water -= c;
            return true;
        }
        return false;
    }
 

    /*
     * MÉTODO MÁS IMPORTANTE, ya que tendremos que hacer 
     * un bloqueo con estados en el momento que el pastelero
     * no tenga suficiente cantidad de harina.
     */
    public void getCantSugar(int c){
        System.out.printf("------>Solicito azucar\n");
        synchronized(objetSugar){  //puedo sincronizar este objeto o un object.
            try{
                while (getSugar() < c && !isFin()){
                    objetSugar.wait(); //No hay harina, por tanto me bloqueo.
                }
                
                if (!isFin()) //Quiero quitar azucar, siempre y cuando esten en horario de trabajo. 
                setSugar(getSugar() - c);

            }catch(InterruptedException e){}

        }
        System.out.printf("<------Obtengo azucar\n");
    }


    
    //seteo la cantidad e azucar. Se notifica a todos los pasteleros.
    public void setCantSugar(int c){
        synchronized(objetSugar){
            setSugar(getSugar() + c);
            objetSugar.notifyAll();
           
        }
        
    }
}