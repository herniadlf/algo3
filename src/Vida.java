package src;
public class Vida {

	private int vida;
	
	public Vida (int vidaDeUnidad){
		this.vida = vidaDeUnidad;
		
	}
	
	public void recibirDanio(int danio){
		this.vida = this.vida - danio;
		
	}
	
	public int obtenerVida(){
		return this.vida;
		
	}
	
	
		
	
	
}
