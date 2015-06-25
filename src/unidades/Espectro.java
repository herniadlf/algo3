package src.unidades;

import src.Danio;
import src.Dinero;
import src.ReglaDeDanioTerran;
import src.Vida;
import src.mapa.Mapeable;


public class Espectro extends Unidad {
	
	private static final int DANIO_AIRE = 20;
	private static final int DANIO_TIERRA = 8;
	private static final int RANGO_ATAQUE = 5;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 8;
	private static final int TRANSPORTE = 0;
	private static final int VIDA = 120;
	private static final int VISION= 7;
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 100;
	
	public Espectro (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Espectro";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		colocador = new ColocadorDeUnidades();
		reglaDeDanio = new ReglaDeDanioTerran();

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

	
	public ColocadorDeUnidades getColocador(){
		
		return colocador;
		
	}

	public boolean esOcupable() {
	
		return false;
		
	}

	public boolean esTerrestre() {
		
		return false;
		
	}

	public boolean esAereo() {
	
		return true;
		
	}

	
	public void atacarConEMP(int danio) {
		
		// no afecta
		
	}

	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		
	}

	public void afectadoPorRadiacion(int danio) {
		
		vida.aumentarDanioARecibir(danio);
		
	}
	
}
