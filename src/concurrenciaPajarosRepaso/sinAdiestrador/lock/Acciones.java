package concurrenciaPajarosRepaso.sinAdiestrador.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	ReentrantLock Rojo;
	Condition Rojocanta;
	Condition Verdecanta;
	Condition Azulcanta;
	ReentrantLock Verde;
	ReentrantLock Azul;
	ReentrantLock cantar;
	int rojo =0;
	int verde =0;
	int azul =0;
	Random random = new Random(); 
	
	public Acciones() {
		this.cantar = new ReentrantLock();
		this.Rojo = new ReentrantLock();
		this.Verde = new ReentrantLock();
		this.Azul = new ReentrantLock();
		this.Rojocanta = Rojo.newCondition();
		this.Verdecanta = Verde.newCondition();
		this.Azulcanta = Azul.newCondition();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		
		while(!cantar.tryLock()) {
			if (raza.equals("Dragon rojo")) {
				Rojo.lock();
				rojo++;
				System.out.println("dragon " + id + " de raza " + raza + " a la espera");
				Rojocanta.await();
				rojo--;
				Rojo.unlock();
			} else if (raza.equals("Dragon verde")) {
				Verde.lock();
				verde++;
				System.out.println("dragon " + id + " de raza " + raza + " a la espera");
				Verdecanta.await();
				verde--;
				Verde.unlock();
			} else if (raza.equals("Dragon azul")) {
				Azul.lock();
				azul++;
				System.out.println("dragon " + id + " de raza " + raza + " a la espera");
				Azulcanta.await();
				azul--;
				Azul.unlock();
			}
		}
		System.out.println("dragon " + id + " de raza " + raza + " preparandose y luego canta");
		Thread.sleep(2500);
		cantar.unlock();
		if (raza.equals("Dragon rojo")) {
			if (verde > 0) {
				Verde.lock();
				Verdecanta.signal();
				Verde.unlock();
			} else if (azul > 0) {
				Azul.lock();
				Azulcanta.signal();
				Azul.unlock();
			} else {
				Rojo.lock();
				Rojocanta.signal();
				Rojo.unlock();
			}

		}else if (raza.equals("Dragon verde")) {
			if (azul > 0) {
				Azul.lock();
				Azulcanta.signal();
				Azul.unlock();
			} else if (rojo > 0) {
				Rojo.lock();
				Rojocanta.signal();
				Rojo.unlock();
			} else {
				Verde.lock();
				Verdecanta.signal();
				Verde.unlock();
			}

		} else if (raza.equals("Dragon azul")) {
			if (verde > 0) {
				Verde.lock();
				Verdecanta.signal();
				Verde.unlock();
			} else if (rojo > 0) {
				Rojo.lock();
				Rojocanta.signal();
				Rojo.unlock();
			} else {
				Azul.lock();
				Azulcanta.signal();
				Azul.unlock();
			}

		}
	}
}