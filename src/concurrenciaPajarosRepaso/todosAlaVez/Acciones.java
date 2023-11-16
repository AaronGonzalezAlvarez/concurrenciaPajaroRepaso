package concurrenciaPajarosRepaso.todosAlaVez;

public class Acciones {

	public void cantar(String raza,int id) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Pajaro con "+ id + " y de raza "+ raza + " esta cantando");
	}
}
