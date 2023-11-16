package concurrenciaPajarosRepaso.todosAlaVez;

public class Principal {
	public static void main(String[] args) {
        int numHilos = 10; // Cambia según la cantidad de hilos que desees lanzar
        Thread[] hilos = new Thread[numHilos];

        long inicio = System.currentTimeMillis();
        Acciones acciones = new Acciones();
        for (int i = 0; i < numHilos; i++) {
            Pajaro pajaro = new Pajaro(acciones,"pe",i);
            hilos[i] = new Thread(pajaro);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;
        
        double tiempoTotalSegundos = tiempoTotal / 1000.0;

        System.out.println("Todos los hilos han terminado. Tiempo total: " + tiempoTotal + " ms y en segondos son: "+ tiempoTotalSegundos);
    }
}
