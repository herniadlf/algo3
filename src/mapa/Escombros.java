package src.mapa;

public class Escombros implements Mapeable {
	
	private String nombre;
	
	public Escombros(){
		
		nombre = "Escombros";
		
	}

	public boolean esLoMismo(Mapeable mapeable) {
		
		return (mapeable.getNombre() == nombre);
		
	}

	public String getNombre() {
		
		return nombre;
		
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
