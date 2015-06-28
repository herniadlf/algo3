package src.unidades;

import excepciones.ExcepcionPosicionInvalida;
import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.Vida;
import src.mapa.Mapeable;

public class AltoTemplario extends Magica {
	
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 7;
	private static final int TRANSPORTE = 2;
	private static final int VIDA = 40;
	private static final int ESCUDO = 40;
	private static final int VISION = 7;
	private static final int COSTO_MINERALES = 50;
	private static final int COSTO_GAS = 150;
	private static int ENERGIA = 50;
	Escudo escudo;
	private int danioRadiacion;
	
	public AltoTemplario() {	
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Alto Templario";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		escudo = new Escudo (ESCUDO,this);
		energia = new Energia(ENERGIA);
		energiaPorTurno = 15;
		colocador = new ColocadorDeUnidades();
		tormentaEnCurso = false;
		afectadoPorRadiacion = false;
		danioRadiacion = 0;
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
		
	}

	public ColocadorDeUnidades getColocador(){
		
		return colocador;
		
	}

	public Escudo getEscudo() {
	
		return escudo;
		
	}

	public int obtenerEnergia() {
	
		return energia.obtenerCantidad();
		
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
		
		energia.disminuirEnergia(danio);
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		AltoTemplario duplicado = new AltoTemplario();
		this.modificarVidaYAtaqueDeUnidadAlucinada(duplicado);
		duplicado.energia = new Energia (0);
		duplicado.energiaPorTurno = 0;
		return duplicado;
		
	}
		
	
}



