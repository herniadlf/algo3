package src.unidades;

import java.util.ArrayList;

import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;

public abstract class Magica extends Unidad {

	
	Energia energia;
	ArrayList<Magia> magias = new ArrayList<Magia>();
	
	
	public void pasoTurno(){
		
		
		
	}
			
	
	@Override
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void atacarEnTierra(Unidad unidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void atacarEnAire(Unidad unidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirDanio(int Danio) {
		// TODO Auto-generated method stub

	}

}
