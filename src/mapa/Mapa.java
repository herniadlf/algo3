package src.mapa;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.ExcepcionPosicionInvalida;

public class Mapa {

	private Map<Posicion,Mapeable> mapa;
	private int tamanio;
	
	private static Mapa INSTANCE = null;
	
	private synchronized static void createInstance() {
		if (INSTANCE == null) { 
	       INSTANCE = new Mapa();
	    }
	}

	public static Mapa getInstance() {
	    if (INSTANCE == null) 
	    	createInstance();
	    return INSTANCE;
	}
	
	public Mapa() {
		
		this.mapa = new HashMap<Posicion,Mapeable>();
		for (int i = 1; i <= this.tamanio; i++) {
			for (int j = 1; j <= this.tamanio; j++) {
				Posicion posicion = new Posicion(i,j);
				Pasto pasto = new Pasto();
				mapa.put(posicion, pasto);
			}
		}
	}
	
	public void colocarEn(int i, int j, Mapeable unElemento) {
		Posicion posicion = new Posicion(i,j);
		try {
			this.validarCoordenadas(posicion);
			mapa.put(posicion, unElemento);
		}
		catch (ExcepcionPosicionInvalida e) {
			
			System.out.println(e.getMessage());
		}

	}
	
	public boolean validarCoordenadas(Posicion unaPosicion) throws ExcepcionPosicionInvalida {
		if (!estaDentroDeLimites(unaPosicion)) {
			throw new ExcepcionPosicionInvalida("Coordendas no validas, fuera de rango");
		}
		return true;
	}

	private boolean estaDentroDeLimites(Posicion unaPosicion) {
		return ((unaPosicion.getX() >= 0) && (unaPosicion.getX() < tamanio)
				&& (unaPosicion.getY() >= 0) && (unaPosicion.getY() < tamanio));
	}
	
	public Mapeable obtenerContenidoEnPosicion(Posicion unaPosicion){
		return this.mapa.get(unaPosicion);
	}
	
	public void eliminarElementoEnPosicion(Posicion unaPosicion){
		Pasto pasto = new Pasto();
		this.mapa.put(unaPosicion,pasto);
	}
		
}