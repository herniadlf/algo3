package src.mapa;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import excepciones.ExcepcionPosicionInvalida;
import src.ConstantesAlgoCraft;
import src.mapa.*;

public class Mapa {

	private Map<Posicion,Mapeable> mapa;
	
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
		for (int i = 1; i <= ConstantesAlgoCraft.TAMANIO_MAPA; i++) {
			for (int j = 1; j <= ConstantesAlgoCraft.TAMANIO_MAPA; j++) {
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
		return ((unaPosicion.getX() > 0) && (unaPosicion.getX() < ConstantesAlgoCraft.TAMANIO_MAPA)
				&& (unaPosicion.getY() > 0) && (unaPosicion.getY() < ConstantesAlgoCraft.TAMANIO_MAPA));
	}
	
	public Mapeable obtenerContenidoEnPosicion(int i, int j){
		Posicion unaPosicion = new Posicion(i,j);
		return this.mapa.get(unaPosicion);
	}
	
	public void eliminarElementoEnPosicion(Posicion unaPosicion){
		Pasto pasto = new Pasto();
		this.mapa.put(unaPosicion,pasto);
	}
	
	public void crearCantidadDeArbolesEnMapa(int cantidadDeArboles){
		if(cantidadDeArboles < (int)(ConstantesAlgoCraft.TAMANIO_MAPA * 0.1) ) {	//10% del tamaño del mapa
			
			Random random = new Random();
			
			for(int k = 1; k <= cantidadDeArboles; k++) {
				
				Arbol unArbol = new Arbol();
				int i = random.nextInt(ConstantesAlgoCraft.TAMANIO_MAPA)+1;
				int j = random.nextInt(ConstantesAlgoCraft.TAMANIO_MAPA)+1;
				this.colocarEn(i, j, unArbol);
			}
			
		}
		
		
	}
		
}