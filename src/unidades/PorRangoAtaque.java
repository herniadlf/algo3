package src.unidades;

import java.util.ArrayList;
import java.util.Iterator;

import src.mapa.Mapa;
import src.mapa.Posicion;

public abstract class PorRangoAtaque extends Magia {
	
	Mapa mapa;
	protected int posicionX;
	protected int posicionY;
	
	protected ArrayList<Posicion> alrededores;	

	public void setAlrededores(){
		
		alrededores = new ArrayList<Posicion>();
		alrededores.add(new Posicion(posicionX-1,posicionY+1));
		alrededores.add(new Posicion(posicionX,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY));
		alrededores.add(new Posicion(posicionX+1,posicionY-1));
		alrededores.add(new Posicion(posicionX,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY));		
	}
		
	public void atacar(){
		
		this.atacarEnEstaPosicion(posicionX, posicionY);
		Iterator<Posicion> i = alrededores.iterator();
		
		while(i.hasNext()){
			Posicion nuevaPosicion = i.next();
			int proximoX = nuevaPosicion.getX();
			int proximoY = nuevaPosicion.getY();
			atacarEnEstaPosicion(proximoX, proximoY);
		}
	
	}
	
	public void atacarEnEstaPosicion(int x ,int y){
		
	}
	
}
