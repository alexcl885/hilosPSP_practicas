public class OunerThread extends Thread{

    private String name;
    

    public OunerThread(String _name){
        name = _name; 
    }
    
    /*
     * Hasta que no me despierte alquien con una interrupción,
     * sigo echandome la siesta. 
     */

    @Override
    public void run(){
        try {
            //me duermo una cantidad ingente de tiempo. juaaaa, juaaaaa, juaaaaa
            Thread.sleep(100000000);     
        } catch (InterruptedException e) {
            System.out.printf("***********SOY EL JEFE %s Y LLEVARÉ LOS MIGUELITOS DE HOY PARA LA FERIA DE ALBACETE DE FECHA (7/09 - 17/09)*********\n", name);
        }
    }
}
