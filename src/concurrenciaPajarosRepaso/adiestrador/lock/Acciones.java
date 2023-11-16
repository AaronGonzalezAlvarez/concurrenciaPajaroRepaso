package concurrenciaPajarosRepaso.adiestrador.lock;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	
	ReentrantLock lock;
	Condition cantarRojo;
	Condition cantarVerde;
	Condition cantarAzul;
	int rojo =0;
	int verde =0;
	int azul =0;
	Random random = new Random(); 
	
	public Acciones() {
		this.lock = new ReentrantLock();
		this.cantarRojo = lock.newCondition();
		this.cantarVerde = lock.newCondition();
		this.cantarAzul = lock.newCondition();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		lock.lock();

		if (raza.equals("Dragon rojo")) {
			rojo++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			cantarRojo.await();
		} else if (raza.equals("Dragon verde")) {
			verde++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			cantarVerde.await();
		} else if (raza.equals("Dragon azul")) {
			azul++;
			System.out.println("dragon " + id + " de raza " + raza + " a la espera");
			cantarAzul.await();
		}
		
		System.out.println("dragon " + id + " de raza " + raza + " preparandose y luego canta");
		Thread.sleep(2500);
		//System.out.println("dragon " + id + " de raza " + raza + " termina y da paso");

		if (raza.equals("Dragon rojo")) {
			rojo--;
			if(lock.hasWaiters(cantarAzul)) {
				cantarAzul.signal();
			}else if(lock.hasWaiters(cantarVerde)) {
				cantarVerde.signal();
			}else {
				cantarRojo.signal();
			}
		} else if (raza.equals("Dragon verde")) {
			verde--;
			if(lock.hasWaiters(cantarAzul)) {
				cantarAzul.signal();
			}else if(lock.hasWaiters(cantarRojo)) {
				cantarRojo.signal();
			}else {
				cantarVerde.signal();
			}
			
		} else if (raza.equals("Dragon azul")) {
			azul--;
			if(lock.hasWaiters(cantarRojo)) {
				cantarRojo.signal();
			}else if(lock.hasWaiters(cantarVerde)) {
				cantarVerde.signal();
			}else {
				cantarAzul.signal();
			}
			
		}

		lock.unlock();
	}

	public void darAviso() {
		lock.lock();
		int numeroAleatorio = random.nextInt(3) + 1;
		if(numeroAleatorio == 1) {
			cantarVerde.signal();
		}else if(numeroAleatorio == 2) {
			cantarRojo.signal();
		}else if(numeroAleatorio == 3) {
			cantarAzul.signal();
		}
		lock.unlock();
		
	}
}
