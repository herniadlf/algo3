package src.mapa;

public class Arbol implements Mapeable{

	private String nombre;
	
	public Arbol(){
		
		this.nombre = "Arbol";
	}
	
	public boolean esLoMismo(Mapeable aComparar){
		
		return (this.getNombre() == aComparar.getNombre());
		
	}
	
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre() {
		
		return this.nombre;
		
	}

	
	public boolean esOcupable() {
		
		return false;
		
	}

	
	public boolean esTerrestre() {
		
		return true;
		
	}

	public boolean esAereo() {
		
		return false;
		
	}

}
