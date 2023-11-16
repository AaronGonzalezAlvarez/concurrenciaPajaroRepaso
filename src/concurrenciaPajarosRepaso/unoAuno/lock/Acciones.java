package concurrenciaPajarosRepaso.unoAuno.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {

	ReentrantLock rojo;
	ReentrantLock verde;
	ReentrantLock azul;

	public Acciones() {
		this.rojo =  new ReentrantLock();
		this.verde =  new ReentrantLock();
		this.azul =  new ReentrantLock();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		if (raza.equals("Dragon rojo")) {
			rojo.lock();
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			rojo.unlock();

		} else if (raza.equals("Dragon verde")) {
			verde.lock();
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			verde.unlock();

		} else if (raza.equals("Dragon azul")) {
			azul.lock();
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			azul.unlock();

		}
	}
}
