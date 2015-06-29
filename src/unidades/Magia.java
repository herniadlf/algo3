package src.unidades;

import java.util.Iterator;
import java.util.LinkedList;

import excepciones.ExcepcionPosicionInvalida;
import src.Atacable;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.mapa.Sector;

public abstract class Magia {

	protected int danio;
	protected int energiaNecesaria;
	protected LinkedList<Posicion> alrededores;	
	protected int posicionX;
	protected int posicionY;
	protected Mapa mapa;
	private Atacable atacadoEnTierra;
	private Atacable atacadoEnAire;
	protected String nombre;
	
	public int obtenerEnergiaNecesaria(){
		
		return energiaNecesaria;
	
	}
	
	public void atacar(){
		
		this.atacarEnEstaPosicion(posicionX, posicionY);
		Iterator<Posicion> i = alrededores.iterator();
		
		while(i.hasNext()){
			Posicion nuevaPosicion = i.next();
			int proximoX = nuevaPosicion.getX();
			int proximoY = nuevaPosicion.getY();
			this.atacarEnEstaPosicion(proximoX, proximoY);
		}
	
	}
	
	abstract void atacarEnEstaPosicion(int x,int y);
	
	public boolean hayAtacableEnTierra(int x, int y){
		
		Sector sector = null;
		try {
			sector = mapa.obtenerContenidoEnPosicion(x, y);
		} catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();
		}
		if (Atacable.class.isAssignableFrom(sector.getElementoEnTierra().getClass())){
			atacadoEnTierra = (Atacable)sector.getElementoEnTierra();
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean hayAtacableEnAire(int x, int y){
		
		Sector sector = null;
		try {
			sector = mapa.obtenerContenidoEnPosicion(x, y);
		} catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();
		}
		if (Atacable.class.isAssignableFrom(sector.getElementoEnAire().getClass())){
			atacadoEnAire = (Atacable)sector.getElementoEnAire();
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public Atacable obtenerAtacadoEnTierra(){
		
		return atacadoEnTierra;

	}
	
	public Atacable obtenerAtacadoEnAire(){
		
		return atacadoEnAire;

	}
	
	public String obtenerNombre(){
		
		return nombre;
		
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
