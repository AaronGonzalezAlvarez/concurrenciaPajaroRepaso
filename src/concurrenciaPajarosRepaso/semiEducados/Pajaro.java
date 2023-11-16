package concurrenciaPajarosRepaso.semiEducados;

import java.util.Random;

public class Pajaro implements Runnable{
	
	Acciones acciones;
	String raza;
	int id;
	Random rand;
	
	public Pajaro(Acciones acciones, String raza, int id) {
		this.acciones = acciones;
		this.raza = raza;
		this.id = id;
		this.rand = new Random();
	}

	public void run() {
		try {
			acciones.cantar(raza,id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
