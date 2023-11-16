package concurrenciaPajarosRepaso.unoAuno.lock;

public class Principal {
	public static void main(String[] args) {
		System.out.println("cantan uno de cada raza a la vez con lock");
	    int numHilos = 15; // Cambia según la cantidad de hilos que desees lanzar
	    Thread[] hilos = new Thread[numHilos];

	    long inicio = System.currentTimeMillis();
	    Acciones acciones = new Acciones();

	    // Crear arrays separados para cada grupo de hilos
	    Thread[] hilosRojo = new Thread[5];
	    Thread[] hilosVerde = new Thread[5];
	    Thread[] hilosAzul = new Thread[5];

	    for (int i = 0; i < 5; i++) {
	        Pajaro pajaro = new Pajaro(acciones, "Dragon rojo", i);
	        hilosRojo[i] = new Thread(pajaro);
	        hilosRojo[i].start();
	    }

	    for (int i = 0; i < 5; i++) {
	        Pajaro pajaro = new Pajaro(acciones, "Dragon verde", i);
	        hilosVerde[i] = new Thread(pajaro);
	        hilosVerde[i].start();
	    }

	    for (int i = 0; i < 5; i++) {
	        Pajaro pajaro = new Pajaro(acciones, "Dragon azul", i);
	        hilosAzul[i] = new Thread(pajaro);
	        hilosAzul[i].start();
	    }

	    // Esperar a que todos los hilos terminen
	    for (int i = 0; i < 5; i++) {
	        try {
	            hilosRojo[i].join();
	            hilosVerde[i].join();
	            hilosAzul[i].join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    long fin = System.currentTimeMillis();
	    long tiempoTotal = fin - inicio;

	    double tiempoTotalSegundos = tiempoTotal / 1000.0;

	    System.out.println("Todos los hilos han terminado. Tiempo total: " + tiempoTotal + " ms y en segundos son: " + tiempoTotalSegundos);
	}
}
