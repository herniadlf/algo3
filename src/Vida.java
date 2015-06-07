package src;
public class Vida {

	private int vida;
	private int danioARecibido;
	
	public Vida (int vidaDeUnidad){
		this.vida = vidaDeUnidad;
		
	}
	
	public void recibirDanio(){
		
		this.vida = this.vida - this.danioARecibido;
		
	}
	
	public int obtenerVida(){
		return this.vida;
		
	}
	
	public void aumentarDanioARecibir( int danio){
		
		this.danioARecibido= this.danioARecibido+ danio;
		
	}
	
	public int obtenerDanioRecibido(){
		return this.danioARecibido;
		
	}
	
	
		
	
	
}
