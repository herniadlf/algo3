package src.unidades;

import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioTerran;
import src.Vida;
import src.mapa.Mapeable;


public class Golliat extends Unidad {

	private static final int DANIO_AIRE = 10;
	private static final int DANIO_TIERRA = 12;
	private static final int RANGO_ATAQUE = 6;//Es el rango de ataque en tierra. Pero tambien tiene rango en aire.
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
	
	public ColocadorDeUnidades getColocador(){
		
		return colocador;
		
	}
	
	public boolean esOcupable() {
		
		return false;
		
	}

	public boolean esTerrestre() {
		
		return true;
		
	}

	public boolean esAereo() {
		
		return false;
		
	}

	public void atacarConEMP(int danio) {
		
		// no afecta a unidades amigas
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		Golliat duplicado = new Golliat();
		this.modificarVidaYAtaqueDeUnidadAlucinada(duplicado);
		return duplicado;
		
	}
	
	public Escudo getEscudo(){
		
		return null;
		
	}
}
