/*package src;

import java.util.Random;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Arbol;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;

public class Ambientador {
	
	public Mapa ambientarMapa(int tamanioMapa, int cantDeArboles, Jugador jugador1, Jugador jugador2) throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos {
		
		Mapa mapa = new Mapa(tamanioMapa);
		
		int posPrincipal1 = (int) ((tamanioMapa) - (tamanioMapa / 4));
		
		int posPrincipal2 = (int) (tamanioMapa - posPrincipal1);
			
		mapa.colocarEn(posPrincipal1, posPrincipal1, jugador1.getRaza().getEdificioPrincipal());		
		
		mapa.colocarEn(posPrincipal2, posPrincipal2, jugador2.getRaza().getEdificioPrincipal());
		
		this.agregarFuentes(mapa, 4, posPrincipal1, posPrincipal1);
		
		this.agregarFuentes(mapa, 4, posPrincipal1, posPrincipal2);
		
		mapa.crearCantidadDeArbolesEnMapa(cantDeArboles);
		
		return mapa;
		
	}
	
	public void agregarFuentes(Mapa mapa, int cantidad, int alrededorDeX, int alrededorDeY){
		
		Random random = new Random();
		
		int totalFuentesCreadas = 0;
		
		while(totalFuentesCreadas != cantidad) {
			
			FuenteDeMinerales unFuenteMineral = new FuenteDeMinerales();
			int i = random.nextInt(cantidad+1)+1;  //Genero números randoms para determinar la posicion de la fuente
			int j = random.nextInt(cantidad+1)+1;
			if(obtenerContenidoEnPosicion(i, j).getElementoEnTierra().esOcupable()){
				
				this.colocarEn(i, j, unArbol);
				totalArbolesCreados++;
			}
		}	
		
		mapa.colocarEn(i, j, unElemento);
		
	}

}
*/