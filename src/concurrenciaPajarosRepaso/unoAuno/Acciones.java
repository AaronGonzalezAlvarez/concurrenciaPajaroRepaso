package concurrenciaPajarosRepaso.unoAuno;

import java.util.concurrent.Semaphore;

public class Acciones {

	Semaphore rojo;
	Semaphore verde;
	Semaphore azul;

	public Acciones() {
		this.azul = new Semaphore(1);
		this.verde = new Semaphore(1);
		this.rojo = new Semaphore(1);
	}

	public void cantar(String raza, int id) throws InterruptedException {
		if (raza.equals("Dragon rojo")) {
			rojo.acquire();
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			rojo.release();

		} else if (raza.equals("Dragon verde")) {
			verde.acquire();
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			verde.release();

		} else if (raza.equals("Dragon azul")) {
			azul.acquire();
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			azul.release();

		}
	}
}
