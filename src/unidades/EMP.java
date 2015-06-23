package src.unidades;

import java.util.ArrayList;
import excepciones.ExcepcionPosicionInvalida;
import src.Atacable;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.mapa.Sector;

public class EMP extends Magia {
	
	protected ArrayList<Posicion> alrededores;	
	
	public EMP(Unidad atacado) {
		
		danio = 20; //supuesto
		energiaNecesaria = 100;
		this.mapa = atacado.getMapa();
		posicionX = atacado.getPosicionX();
		posicionY = atacado.getPosicionY();
		this.setAlrededores();
		nombre = "EMP";
		
	}
	
	public void atacarEnEstaPosicion(int x,int y){
		
		if(this.hayAtacableEnTierra(x, y)){
			
			this.obtenerAtacadoEnTierra().atacarConEMP(danio);
			
		}	
	}
	
}
