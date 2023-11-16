package concurrenciaPajarosRepaso.adiestrador.sync;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	Object cantarRojo;
	Object cantarVerde;
	Object cantarAzul;
	int rojo =0;
	int verde =0;
	int azul =0;
	Random random = new Random(); 
	
	public Acciones() {
		this.cantarRojo = new Object();
		this.cantarVerde = new Object();
		this.cantarAzul = new Object();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		if (raza.equals("Dragon rojo")) {
			rojo++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			synchronized (cantarRojo) {
				cantarRojo.wait();
			}
		} else if (raza.equals("Dragon verde")) {
			verde++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			synchronized (cantarVerde) {
				cantarVerde.wait();
			}
		} else if (raza.equals("Dragon azul")) {
			azul++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			synchronized (cantarAzul) {
				cantarAzul.wait();
			}
		}
		System.out.println("dragon " + id + " de raza " + raza + " preparandose y luego canta");
		Thread.sleep(2500);

		if (raza.equals("Dragon rojo")) {
			rojo--;
			if (verde > 0) {
				synchronized (cantarVerde) {
					cantarVerde.notify();
				}
			} else if (azul > 0) {
				synchronized (cantarAzul) {
					cantarAzul.notify();
				}
			} else {
				synchronized (cantarRojo) {
					cantarRojo.notify();
				}
			}

		} else if (raza.equals("Dragon verde")) {
			verde--;
			if (azul > 0) {
				synchronized (cantarAzul) {
					cantarAzul.notify();
				}
			} else if (rojo > 0) {
				synchronized (cantarRojo) {
					cantarRojo.notify();
				}
			} else {
				synchronized (cantarVerde) {
					cantarVerde.notify();
				}
			}

		} else if (raza.equals("Dragon azul")) {
			azul--;
			if (verde > 0) {
				synchronized (cantarVerde) {
					cantarVerde.notify();
				}
			} else if (rojo > 0) {
				synchronized (cantarRojo) {
					cantarRojo.notify();
				}
			} else {
				synchronized (cantarAzul) {
					cantarAzul.notify();
				}
			}

		}
	}

	public void darAviso() {
		int numeroAleatorio = random.nextInt(3) + 1;
		if (numeroAleatorio == 1) {
			synchronized (cantarVerde) {
				cantarVerde.notify();
			}
		} else if (numeroAleatorio == 2) {
			synchronized (cantarAzul) {
				cantarAzul.notify();
			}
		} else if (numeroAleatorio == 3) {
			synchronized (cantarRojo) {
				cantarRojo.notify();
			}
		}

	}
}
