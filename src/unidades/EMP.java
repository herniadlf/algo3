package src.unidades;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.ExcepcionPosicionInvalida;
import src.Atacable;
import src.GasVespeno;
import src.Mineral;
import src.Recurso;
import src.mapa.EspacioDisponible;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.mapa.Posicion;
import src.mapa.Sector;

public class EMP extends PorRangoAtaque {

	Mapa mapa;
	private int posicionX;
	private int posicionY;
	
	protected ArrayList<Posicion> alrededores;	
	
	public EMP(Mapa mapa, int x, int y) {
		
		danio = 20; //supuesto
		energiaNecesaria = 100;
		this.mapa = mapa;
		posicionX = x;
		posicionY = y;
		this.setAlrededores();
		
	}
	
	
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

	public void atacarEnEstaPosicion(int x,int y){
		
		try {
			Sector sector = mapa.obtenerContenidoEnPosicion(x, y);
			if(!(sector.getElementoEnTierra().getClass() == new EspacioDisponible().getClass())){
				if(!(sector.getElementoEnTierra().getClass() == new FuenteDeRecurso().getClass())){
					Atacable objetoEnTierra = (Atacable)sector.getElementoEnTierra();
					objetoEnTierra.atacarConEMP(danio);		
				}		
			}	
			
		} catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();	
		}	
	
	}
	
}
