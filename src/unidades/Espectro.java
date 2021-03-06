package src.unidades;

import excepciones.ExcepcionPosicionInvalida;
import src.Danio;
import src.Dinero;
import src.Escudo;
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
	
	public Unidad duplicarConAlucinacion() {
		
		Espectro duplicado = new Espectro();
		this.modificarVidaYAtaqueDeUnidadAlucinada(duplicado);
		return duplicado;
		
	}
		
	public Escudo getEscudo(){
		
		return null;
		
	}

	
}
