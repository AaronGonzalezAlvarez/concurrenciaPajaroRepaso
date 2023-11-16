package concurrenciaPajarosRepaso.adiestrador.semaphoro;

import java.util.Random;

public class Adiestrador implements Runnable{
	
	Acciones acciones;
	String raza;
	int id;
	Random rand;
	
	public Adiestrador(Acciones acciones) {
		this.acciones = acciones;
	}

	public void run() {
		try {
			Thread.sleep(1500);
			acciones.darAviso();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
