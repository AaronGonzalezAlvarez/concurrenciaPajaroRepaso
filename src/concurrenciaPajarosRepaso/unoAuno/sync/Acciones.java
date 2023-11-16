package concurrenciaPajarosRepaso.unoAuno.sync;

public class Acciones {

	Object rojo;
	Object verde;
	Object azul;

	public Acciones() {
		this.azul = new Object();
		this.verde = new Object();
		this.rojo = new Object();
	}

	public void cantar(String raza, int id) throws InterruptedException {
		if (raza.equals("Dragon rojo")) {
			synchronized (rojo) {
				Thread.sleep(1000);
				System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			}
		} else if (raza.equals("Dragon verde")) {
			synchronized (verde) {
				Thread.sleep(1000);
				System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			}
		} else if (raza.equals("Dragon azul")) {
			synchronized (azul) {
				Thread.sleep(1000);
				System.out.println("Pajaro con " + id + " y de raza " + raza + " esta cantando");
			}
		}
	}
}
