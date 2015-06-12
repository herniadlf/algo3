package src;

import java.util.Random;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;

public class Ambientador {
	
	private static int CANT_DE_FUENTES_RECURSOS = 4;
	
	private static int DISTANCIA_MAXIMA = 10;
	
	public Mapa ambientarMapa(int tamanioMapa, int cantDeArboles, Jugador jugador1, Jugador jugador2) throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos {
		
		Mapa mapa = new Mapa(tamanioMapa);
		
		int posPrincipal1 = (int) ((tamanioMapa) - (tamanioMapa / 4));
		
		int posPrincipal2 = (tamanioMapa - posPrincipal1);
			
		try {
			
			mapa.colocarEn(posPrincipal1, posPrincipal1, jugador1.getRaza().getEdificioPrincipal());		
			
			mapa.colocarEn(posPrincipal2, posPrincipal2, jugador2.getRaza().getEdificioPrincipal());
			
			this.agregarFuentes(mapa, CANT_DE_FUENTES_RECURSOS, posPrincipal1, posPrincipal1);
			
			this.agregarFuentes(mapa, CANT_DE_FUENTES_RECURSOS, posPrincipal1, posPrincipal2);
			
			mapa.crearCantidadDeArbolesEnMapa(cantDeArboles);
			
		}
		
		catch (ExcepcionSuperaLimenteDeArbolesPermitos e) {
			
			mapa.crearCantidadDeArbolesEnMapa((int) (mapa.getTamanioMapa()*0.1));
			
		}
		
		return mapa;

	}
	
	public void agregarFuentes(Mapa mapa, int cantidadDeseada, int alrededorDeX, int alrededorDeY) throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		Random random = new Random();
		
		int totalFuentesDeMineral = 0;
		
		while(totalFuentesDeMineral != cantidadDeseada) {
			
			int i = random.nextInt(DISTANCIA_MAXIMA);  //Genero números randoms para determinar la posicion de la fuente
			int j = random.nextInt(DISTANCIA_MAXIMA);
			
			try {
				
				mapa.colocarEn(alrededorDeX + i, alrededorDeY + j, new FuenteDeMinerales());
				totalFuentesDeMineral++;
				
				}
			
			catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion e) {
				
				}	
		}
		
		int totalFuentesDeGasVespeno = 0;
		
		while(totalFuentesDeGasVespeno != cantidadDeseada) {
			
			int i = random.nextInt(DISTANCIA_MAXIMA);  //Genero números randoms para determinar la posicion de la fuente
			int j = random.nextInt(DISTANCIA_MAXIMA);
			
			try {
				
				mapa.colocarEn(alrededorDeX + i, alrededorDeY + j, new FuenteDeGasVespeno());
				totalFuentesDeGasVespeno++;
				
				}
			
			catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion e) {
				
				}	
		}
	}

}