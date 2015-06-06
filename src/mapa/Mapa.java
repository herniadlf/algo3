package src.mapa;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import src.ConstantesAlgoCraft;
import src.mapa.*;

public class Mapa {

	private Map<Posicion,Mapeable> mapa;
	private int tamanio;
	
	public int getTamanioMapa(){
		
		return this.tamanio;
	}
	
	public Mapa(int unTamanio) {
		
		this.tamanio = unTamanio;
		this.mapa = new HashMap<Posicion,Mapeable>();
		
		for (int i = 1; i <= tamanio; i++) {
			for (int j = 1; j <= tamanio; j++) {
				
				Posicion posicion = new Posicion(i,j);
				Pasto pasto = new Pasto();
				mapa.put(posicion, pasto);
			}
		}
	}
	
	public void colocarEn(int i, int j, Mapeable unElemento) throws ExcepcionPosicionInvalida {
		
		Posicion posicion = new Posicion(i,j);
		this.validarCoordenadas(posicion);
		mapa.put(posicion, unElemento);

	}
	
	public boolean validarCoordenadas(Posicion unaPosicion) throws ExcepcionPosicionInvalida {
		
		if (!estaDentroDeLimites(unaPosicion)) {
			throw new ExcepcionPosicionInvalida("Coordenadas fuera del rango del mapa");
		}
		return true;
	}

	private boolean estaDentroDeLimites(Posicion unaPosicion) {
		
		return ((unaPosicion.getX() > 0) && (unaPosicion.getX() <= tamanio)
				&& (unaPosicion.getY() > 0) && (unaPosicion.getY() <= tamanio));
	}
	
	public Mapeable obtenerContenidoEnPosicion(int i, int j) throws ExcepcionPosicionInvalida{
		
		Posicion unaPosicion = new Posicion(i,j);
		this.validarCoordenadas(unaPosicion);
		return this.mapa.get(unaPosicion);
	}
	
	public void eliminarElementoEnPosicion(int i, int j) throws ExcepcionPosicionInvalida{
		
		Posicion unaPosicion = new Posicion(i,j);
		this.validarCoordenadas(unaPosicion);
		Pasto pasto = new Pasto();
		this.mapa.put(unaPosicion,pasto);
	}
	
	public void crearCantidadDeArbolesEnMapa(int cantidadDeArboles) throws ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos{
		
		if(cantidadDeArboles <= (int)(tamanio * 0.1) ) {	  //Como máximo el 10% del tamaño del mapa
			
			Random random = new Random();
			
			for(int k = 1; k <= cantidadDeArboles; k++) {
				
				Arbol unArbol = new Arbol();
				int i = random.nextInt(tamanio)+1;  //Genero números randoms para determinar la posicion del Arbol
				int j = random.nextInt(tamanio)+1;
				this.colocarEn(i, j, unArbol);
			}
			
		}
		
		else {
			
			throw new ExcepcionSuperaLimenteDeArbolesPermitos("La cantidad de arboles no debe superar el 10% del tamanio del mapa");
			
		}
		
	}
	
	public int distanciaEntreLosPuntos(int x1, int y1, int x2, int y2) {
		
		return ((int) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)));
	}
	
}