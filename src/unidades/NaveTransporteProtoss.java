package src.unidades;

import java.util.ArrayList;

import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.Vida;
import src.mapa.Mapeable;
import test.NaveTransporteProtossTest;

public class NaveTransporteProtoss extends DeTransporte {
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 5;
	private static final int TRANSPORTE = 8;// capacidad
	private static final int VIDA = 80;
	private static final int ESCUDO = 60;
	private static final int VISION = 8;
	private static final int COSTO_MINERALES = 200;
	private static final int COSTO_GAS = 0;	
	Escudo escudo;
	
	public NaveTransporteProtoss() {		
	
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Nave Transporte Protoss";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		escudo = new Escudo (ESCUDO,this);
		colocador = new ColocadorDeUnidades();
		unidadesAbordo = new ArrayList<Unidad>();
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
	}	
		
	public void pasoTurno() {
		
		escudo.pasoTurno();
		super.pasoTurno();
	}

	public ColocadorDeUnidades getColocador(){
		
		return colocador;
		
	}

	public boolean esOcupable() {
		
		return false;
		
	}


	public void atacarConEMP(int danio) {
		
		escudo.atacar(danio);
		
	}

	public void afectadoPorTormentaPsionica(int danio){
		
		escudo.atacar(danio);
		
	}
	
	public Escudo getEscudo(){
		
		return escudo;
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		NaveTransporteProtoss duplicado = new NaveTransporteProtoss();
		this.modificarVidaYAtaqueDeUnidadAlucinada(duplicado);
		return duplicado;
		
	}
		
	
}
