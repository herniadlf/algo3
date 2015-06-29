package src.mapa;


public interface Mapeable {
	
	boolean esLoMismo(Mapeable m);
	String getNombre();
	boolean esOcupable();
	boolean esTerrestre();
	boolean esAereo();
	
}
