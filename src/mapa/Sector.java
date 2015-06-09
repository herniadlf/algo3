package src.mapa;

public class Sector implements Mapeable{

	private Mapeable elementoEnTierra;
	
	private Mapeable elementoEnAire;
	
	public Sector(Mapeable elementoEnTierra, Mapeable elementoEnAire) {
		
		this.elementoEnTierra = elementoEnTierra;
		this.elementoEnAire = elementoEnAire;
		
	}
	
	@Override
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esLoMismo(Mapeable m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
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

	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return true;
	}

}
