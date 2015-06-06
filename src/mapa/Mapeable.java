package src.mapa;


public interface Mapeable {
	
	Mapeable colocarContenido();
	Mapeable dibujar();
	Mapeable quitarContenido();
	Mapeable mover();
	boolean esLoMismo(Mapeable m);
	String getNombre();
	boolean esPisable();
}
