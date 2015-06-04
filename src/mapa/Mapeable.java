package src.mapa;


public interface Mapeable {
	
	Mapeable colocarContenido();
	Mapeable dibujar();
	Mapeable quitarContenido();
	Mapeable mover();
	String getNombre();
}
