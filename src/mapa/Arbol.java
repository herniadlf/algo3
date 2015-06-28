package src.mapa;

public class Arbol implements Mapeable{

	private String nombre;
	
	public Arbol(){
		
		this.nombre = "Arbol";
	}
	
	public boolean esLoMismo(Mapeable aComparar){
		
		return (this.getNombre() == aComparar.getNombre());
		
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
