package concurrenciaPajarosRepaso.sinAdiestrador.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	Semaphore cantarRojo;
	Semaphore cantarVerde;
	Semaphore cantarAzul;
	Semaphore cantar;
	int rojo =0;
	int verde =0;
	int azul =0;
	Random random = new Random(); 
	
	public Acciones() {
		this.cantarRojo = new Semaphore(0);
		this.cantarVerde = new Semaphore(0);
		this.cantarAzul = new Semaphore(0);
		this.cantar = new Semaphore(1);
	}

	public void cantar(String raza, int id) throws InterruptedException {
		
		while(!cantar.tryAcquire()) {
			if (raza.equals("Dragon rojo")) {
				rojo++;
				System.out.println("dragon " + id + " de raza " + raza + " a la espera");
				cantarRojo.acquire();
				rojo--;
			} else if (raza.equals("Dragon verde")) {
				verde++;
				System.out.println("dragon " + id + " de raza " + raza + " a la espera");
				cantarVerde.acquire();
				verde--;
			} else if (raza.equals("Dragon azul")) {
				azul++;
				System.out.println("dragon " + id + " de raza " + raza + " a la espera");
				cantarAzul.acquire();
				azul--;
			}
		}
		System.out.println("dragon " + id + " de raza " + raza + " preparandose y luego canta");
		Thread.sleep(2500);
		cantar.release();
		if (raza.equals("Dragon rojo")) {
			if (verde > 0) {
				cantarVerde.release();
			} else if (azul > 0) {
				cantarAzul.release();
			} else {
				cantarRojo.release();
			}

		} else if (raza.equals("Dragon verde")) {
			if (azul > 0) {
				cantarAzul.release();
			} else if (rojo > 0) {
				cantarRojo.release();
			} else {
				cantarVerde.release();
			}

		} else if (raza.equals("Dragon azul")) {
			if (verde > 0) {
				cantarVerde.release();
			} else if (rojo > 0) {
				cantarRojo.release();
			} else {
				cantarAzul.release();
			}

		}
	}
}
