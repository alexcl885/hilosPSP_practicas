/**
 * Hilo main
 */
public class Minecraft {

    public static void main(String[] args) {
        RecursoFunducion recurso = new RecursoFunducion();

        Persona marcos = new Persona("Marco", recurso);
        Persona alex = new Persona("Alex", recurso);

        Horno horno = new Horno(1 , recurso);

        horno.start();

        marcos.start();
        alex.start();

        try {
            horno.join();
            marcos.join();
            alex.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("##Programa terminado");

    }
}