package src;

import src.mapa.Mapeable;

public interface Atacable extends Mapeable{
	
	public int getPosicionX();
	
	public int getPosicionY();

	public Vida getVida();
	
	public void atacarConEMP(int danio);
	
	public void recibirDanio();

}
