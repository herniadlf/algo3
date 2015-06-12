package src;
public class Vida {

	private int vida;
	private int danioRecibido;
	private boolean finalizada;
	
	public Vida (int vidaDeUnidad){
		
		this.vida = vidaDeUnidad;
		this.finalizada= false;
		this.danioRecibido = 0;
		
	}
	
	public void dismunuirVidaPorDanio(){
		
		this.vida = this.vida - this.danioRecibido;
		this.actualizarEstadoDeVida();
		
	}
	
	public int obtenerVida(){
		
		return this.vida;
		
	}
	
	public void aumentarDanioARecibir( int danio){
		
		this.danioRecibido= this.danioRecibido+ danio;
		
	}
	
	public int obtenerDanioRecibido(){
		
		return this.danioRecibido;
		
	}
	
	public void actualizarEstadoDeVida (){
		
		if (this.vida<=0){
			this.finalizada= true;	
		}
		
	}
	
	public boolean devolverEstadoDeVida (){
		
		return this.finalizada;
		
	}
	
}
