package src.unidades;

import java.util.ArrayList;

import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapeable;

public abstract class DeTransporte extends Unidad {

	protected int cantidadPasajeros;
	protected ArrayList<Unidad> unidadesAbordo;
	private static final int TRANSPORTE = 8;
	
	public void llevar(Unidad unidad) throws ExcepcionElTransporteEstaLleno{
		
		if(cantidadPasajeros + unidad.getTransporte() <= TRANSPORTE){
			
			unidadesAbordo.add(unidad);
			cantidadPasajeros = cantidadPasajeros + unidad.getTransporte();
		
		}
		else {
			
			throw new ExcepcionElTransporteEstaLleno("El transporte esta lleno");
			
		}
		
		}
	
	public int cantidadPasajeros() {
		
		return cantidadPasajeros;
		
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
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recibirDanio() throws ExcepcionPosicionInvalida {
		// TODO Auto-generated method stub

	}

}
