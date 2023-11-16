package concurrenciaPajarosRepaso.sinAdiestrador.sync;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	Object cantarRojo;
	Object cantarVerde;
	Object cantarAzul;
	Object cantar;
	int rojo =0;
	int verde =0;
	int azul =0;
	Random random = new Random(); 
	private boolean bloqueoDisponible = true;
	
	public Acciones() {
		this.cantarRojo = new Object();
		this.cantarVerde = new Object();
		this.cantarAzul = new Object();
		this.cantar = new Object();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		synchronized (cantar) {
			while (!bloqueoDisponible) {
				if (raza.equals("Dragon rojo")) {
					synchronized (cantarRojo) {
						rojo++;
						System.out.println("dragon " + id + " de raza " + raza + " a la espera");
						cantarRojo.wait();
					}
				} else if (raza.equals("Dragon verde")) {
					synchronized (cantarVerde) {
						verde++;
						System.out.println("dragon " + id + " de raza " + raza + " a la espera");
						cantarVerde.wait();
					}
				} else if (raza.equals("Dragon azul")) {
					synchronized (cantarAzul) {
						azul++;
						System.out.println("dragon " + id + " de raza " + raza + " a la espera");
						cantarAzul.wait();
					}
				}
			}

			// Sección crítica: código que requiere el bloqueo
			System.out.println("dragon " + id + " de raza " + raza + " preparandose y luego canta");
			Thread.sleep(2500);
			if (raza.equals("Dragon rojo")) {
				rojo--;
				if (verde > 0) {
					synchronized (cantarVerde) {
						cantarVerde.notifyAll();
					}
				} else if (azul > 0) {
					synchronized (cantarAzul) {
						cantarAzul.notifyAll();
					}
				} else {
					synchronized (cantarRojo) {
						cantarRojo.notifyAll();
					}
				}

			} else if (raza.equals("Dragon verde")) {
				verde--;
				if (azul > 0) {
					synchronized (cantarAzul) {
						cantarAzul.notifyAll();
					}
				} else if (rojo > 0) {
					synchronized (cantarRojo) {
						cantarRojo.notifyAll();
					}
				} else {
					synchronized (cantarVerde) {
						cantarVerde.notifyAll();
					}
				}

			} else if (raza.equals("Dragon azul")) {
				azul--;
				if (verde > 0) {
					synchronized (cantarVerde) {
						cantarVerde.notifyAll();
					}
				} else if (rojo > 0) {
					synchronized (cantarRojo) {
						cantarRojo.notifyAll();
					}
				} else {
					synchronized (cantarAzul) {
						cantarAzul.notifyAll();
					}
				}

			}

			// Cambia el estado del bloqueo
			bloqueoDisponible = false;
		}
	}
}
