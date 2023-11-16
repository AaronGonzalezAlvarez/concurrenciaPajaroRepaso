package concurrenciaPajarosRepaso.semiEducados;

public class Acciones {
	
	Object o1;
	
	public Acciones() {
		this.o1 = new Object();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		synchronized (o1) {
			Thread.sleep(1000);
			System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
		}
	}
}
