package src.mapa;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.construcciones.Extractora;

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
				Sector unSector = new Sector(new EspacioDisponible(), new EspacioDisponible());
				mapa.put(posicion, unSector);
			}
		}
		
	}
	
	public void colocarEn(int i, int j, Mapeable unElemento) throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Posicion posicion = new Posicion(i,j);
		this.validarCoordenadas(posicion);
		this.validarPosicionSinElemento(posicion, unElemento.esTerrestre());
		if (unElemento.esTerrestre()){
			this.obtenerContenidoEnPosicion(i, j).setElementoEnTierra(unElemento);
		}
		else {
			this.obtenerContenidoEnPosicion(i, j).setElementoEnAire(unElemento);
		}

	}
	
	public void colocarExtractorEn(int i, int j, Extractora unExtractor) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso {
		
		Posicion posicion = new Posicion(i,j);
		this.validarCoordenadas(posicion);
		this.obtenerContenidoEnPosicion(i, j).setElementoEnTierra(unExtractor);		
		
	}
	
	public void verificarFuente(int x, int y,Extractora unExtractor) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		if (!obtenerContenidoEnPosicion(x, y).getElementoEnTierra().esLoMismo(unExtractor.getFuente())) {			
			throw new ExcepcionExtractoraSinRecurso("No se puede construir edificio extractor sin fuente de recursos.");
		}
		else {
			this.obtenerContenidoEnPosicion(x, y).setElementoEnTierra(new Escombros());
		}
		
	}
	
	public void validarPosicionSinElemento(Posicion posicion, boolean esTerrestre) throws 
		ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion {
		
		Sector unSector = obtenerContenidoEnPosicion(posicion.getX(), posicion.getY());
		
		if((esTerrestre) && !unSector.getElementoEnTierra().esOcupable()){
			
			throw new ExcepcionYaHayElementoEnLaPosicion("Ya hay un elemento de Tierra en la posicion");
		}
		
		else if ((!esTerrestre) && !unSector.getElementoEnAire().esOcupable()) {
			
			throw new ExcepcionYaHayElementoEnLaPosicion("Ya hay un elemento de Aire en la posicion");
			
		}
		
	}

	private boolean validarCoordenadas(Posicion unaPosicion) throws ExcepcionPosicionInvalida {
		
		if (!estaDentroDeLimites(unaPosicion)) {
			throw new ExcepcionPosicionInvalida("Coordenadas fuera del rango del mapa");
		}
		
		return true;
	}

	private boolean estaDentroDeLimites(Posicion unaPosicion) {
		
		return ((unaPosicion.getX() > 0) && (unaPosicion.getX() <= tamanio) && (unaPosicion.getY() > 0) && (unaPosicion.getY() <= tamanio));
	}
	
	public Sector obtenerContenidoEnPosicion(int i, int j) throws ExcepcionPosicionInvalida{
		
		Posicion unaPosicion = new Posicion(i,j);
		this.validarCoordenadas(unaPosicion);
		return (Sector) this.mapa.get(unaPosicion);
		
	}
	
	public void eliminarElementoTerrestreEnPosicion(int i, int j) throws ExcepcionPosicionInvalida{
		
		Posicion unaPosicion = new Posicion(i,j);
		this.validarCoordenadas(unaPosicion);
		this.obtenerContenidoEnPosicion(i, j).setElementoEnTierra(new EspacioDisponible());
	
	}
	
	public void eliminarElementoAereoEnPosicion(int i, int j) throws ExcepcionPosicionInvalida{
		
		Posicion unaPosicion = new Posicion(i,j);
		this.validarCoordenadas(unaPosicion);
		this.obtenerContenidoEnPosicion(i, j).setElementoEnAire(new EspacioDisponible());
	
	}
	
	public void crearCantidadDeArbolesEnMapa(int cantidadDeArboles) throws 
		ExcepcionPosicionInvalida, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionYaHayElementoEnLaPosicion{
		
		if(cantidadDeArboles <= (int)(tamanio * 0.1) ) { //Como máximo el 10% del tamaño del mapa
			
			Random random = new Random();
			int totalArbolesCreados = 0;
			
			while(totalArbolesCreados != cantidadDeArboles) {
			
				int i = random.nextInt(tamanio)+1;  //Genero números randoms para determinar la posicion del Arbol
				int j = random.nextInt(tamanio)+1;
				if(obtenerContenidoEnPosicion(i, j).getElementoEnTierra().esOcupable()){
					
					this.colocarEn(i, j, new Arbol());
					totalArbolesCreados++;
				}
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