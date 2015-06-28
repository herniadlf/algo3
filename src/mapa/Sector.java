package src.mapa;

public class Sector implements Mapeable{

	private Mapeable elementoEnTierra;
	
	private Mapeable elementoEnAire;
	
	public Sector(Mapeable elementoEnTierra, Mapeable elementoEnAire) {
		
		this.elementoEnTierra = elementoEnTierra;
		this.elementoEnAire = elementoEnAire;
		
	}

	public boolean esLoMismo(Mapeable m) {
		
		return false;
		
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean esOcupable() {
		
		return false;
		
	}
	
	public Mapeable getElementoEnTierra() {
		
		return this.elementoEnTierra;
		
	}
	
	public void setElementoEnTierra(Mapeable unElemento){
		
		this.elementoEnTierra = unElemento;
		
	}
	
	public Mapeable getElementoEnAire() {
		
		return this.elementoEnAire;
		
	}
	
	public void setElementoEnAire(Mapeable unElemento){
		
		this.elementoEnAire = unElemento;
		
	}

	public boolean esTerrestre() {
		
		return true;
		
	}

	public boolean esAereo() {
	
		return true;
		
	}

}
