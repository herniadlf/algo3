package src.mapa;

public class EspacioDisponible implements Mapeable{
	
	private String nombre;
	
	public EspacioDisponible(){
		
		nombre = "Espacio Disponible";
		
	}
	
	public boolean esLoMismo(Mapeable aComparar){
		
		return (this.getNombre() == aComparar.getNombre());
		
	}

	public String getNombre() {
		
		return nombre;
		
	}
	
	public boolean esOcupable() {
		
		return true;
		
	}
	
	public boolean esTerrestre() {
		
		return true;
		
	}
	
	public boolean esAereo() {
		
		return true;
		
	}

}

