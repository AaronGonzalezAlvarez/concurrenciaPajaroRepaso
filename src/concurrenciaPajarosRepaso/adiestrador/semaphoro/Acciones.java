package concurrenciaPajarosRepaso.adiestrador.semaphoro;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	Semaphore cantarRojo;
	Semaphore cantarVerde;
	Semaphore cantarAzul;
	int rojo =0;
	int verde =0;
	int azul =0;
	Random random = new Random(); 
	
	public Acciones() {
		this.cantarRojo = new Semaphore(0);
		this.cantarVerde = new Semaphore(0);
		this.cantarAzul = new Semaphore(0);
	}

	public void cantar(String raza, int id) throws InterruptedException {
		if (raza.equals("Dragon rojo")) {
			rojo++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			cantarRojo.acquire();
		} else if (raza.equals("Dragon verde")) {
			verde++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			cantarVerde.acquire();
		} else if (raza.equals("Dragon azul")) {
			azul++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			cantarAzul.acquire();
		}
		System.out.println("dragon " + id + " de raza " + raza + " preparandose y luego canta");
		Thread.sleep(2500);

		if (raza.equals("Dragon rojo")) {
			rojo--;
			if (verde > 0) {
				cantarVerde.release();
			} else if (azul > 0) {
				cantarAzul.release();
			} else {
				cantarRojo.release();
			}

		} else if (raza.equals("Dragon verde")) {
			verde--;
			if (azul > 0) {
				cantarAzul.release();
			} else if (rojo > 0) {
				cantarRojo.release();
			} else {
				cantarVerde.release();
			}

		} else if (raza.equals("Dragon azul")) {
			azul--;
			if (verde > 0) {
				cantarVerde.release();
			} else if (rojo > 0) {
				cantarRojo.release();
			} else {
				cantarAzul.release();
			}

		}
	}

	public void darAviso() throws InterruptedException {
		int numeroAleatorio = random.nextInt(3) + 1;
		if (numeroAleatorio == 1) {
			cantarAzul.release();
		} else if (numeroAleatorio == 2) {
			cantarRojo.release();
		} else if (numeroAleatorio == 3) {
			cantarVerde.release();
		}

	}
}
