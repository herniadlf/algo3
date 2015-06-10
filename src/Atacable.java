package src;

import src.mapa.Mapeable;

public interface Atacable extends Mapeable{
	
	public Vida getVida();
	
	public void recibirDanio();

}
