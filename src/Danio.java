package src;

public class Danio {
	
	private int danioTierra;
	private int danioAire;

	public Danio(int danioAire2, int danioTierra2) {
		
		danioAire = danioAire2;
		danioTierra = danioTierra2;
		
	}
	
	public void setDanioAire(int danio) {
		
		this.danioAire = danio;
		
	}
	
	public void setDanioTierra (int danio) {
		
		this.danioTierra = danio;
		
	}
	
	public int getDanioAire() {
		
		return this.danioAire;
	}
	
	public int getDanioTierra() {
		
		return this.danioTierra;
		
	}
		
}
	
	

