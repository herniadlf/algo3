package src.unidades;

import excepciones.ExcepcionPosicionInvalida;
import src.Atacable;
import src.mapa.Mapeable;

public abstract class Magica extends Unidad {

	int energiaPorTurno;
	Energia energia;
	//ArrayList<Magia> magias = new ArrayList<Magia>();
	
	
	public void pasoTurno(){
		
		energia.aumentarEnergia(energiaPorTurno);
		
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
	public void atacarEnTierra(Atacable atacado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void atacarEnAire(Atacable atacado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirDanio() throws ExcepcionPosicionInvalida {
		// TODO Auto-generated method stub

	}

}
