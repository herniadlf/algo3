package src.unidades;

import java.util.LinkedList;

import src.mapa.Posicion;

public abstract class Magia {

	protected int danio;
	protected int energiaNecesaria;
	protected LinkedList<Posicion> alrededores;	
	protected int posicionX;
	protected int posicionY;
	
	public int obtenerEnergiaNecesaria(){
		
		return energiaNecesaria;
	
	}
	
	public void setAlrededores(){
		
		alrededores = new LinkedList<Posicion>();
		alrededores.add(new Posicion(posicionX-1,posicionY+1));
		alrededores.add(new Posicion(posicionX,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY));
		alrededores.add(new Posicion(posicionX+1,posicionY-1));
		alrededores.add(new Posicion(posicionX,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY));		
		
	}
		
	
}
