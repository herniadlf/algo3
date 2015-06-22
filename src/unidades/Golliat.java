package src.unidades;

import src.Danio;
import src.Dinero;
import src.ReglaDeDanioTerran;
import src.Vida;
import src.mapa.Mapeable;


public class Golliat extends Unidad {

	private static final int DANIO_AIRE = 10;
	private static final int DANIO_TIERRA = 12;
	private static final int RANGO_ATAQUE = 6;//es el rango de ataque en tierra.. pero tambien tiene rango en aire.:S
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 6;
	private static final int TRANSPORTE = 2;
	private static final int VIDA = 125;
	private static final int VISION= 8;	
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 50;
		
	public Golliat (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Golliat";
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
	
	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void atacarConEMP(int danio) {
		// no afecta
		
	}

	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		
	}

	@Override
	public void pasoTurno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afectadoPorRadiacion(int danio) {
		
		vida.aumentarDanioARecibir(danio);
		
	}
	
	
	

}
