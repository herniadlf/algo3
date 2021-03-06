package src.unidades;



import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.Vida;



public class Zealot extends Unidad {

	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 8;
	private static final int RANGO_ATAQUE = 1;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 4;
	private static final int TRANSPORTE = 2;
	private static final int VIDA = 100;
	private static final int ESCUDO = 60;
	private static final int VISION= 7;
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	Escudo escudo;
	
	public Zealot (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Zealot";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		escudo = new Escudo (ESCUDO,this);
		colocador = new ColocadorDeUnidades();
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
	}		
	
	public void pasoTurno() {
		
		escudo.pasoTurno();
		super.pasoTurno();
	}
		
	public Escudo getEscudo(){
		
		return escudo;
		
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
		
		escudo.atacar(danio);
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		Zealot duplicado = new Zealot();
		this.modificarVidaYAtaqueDeUnidadAlucinada(duplicado);
		return duplicado;
		
	}
		

	
}