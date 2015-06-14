package src.unidades;

import src.Danio;
import src.Dinero;
import src.Escudo;
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
	private boolean tormentaEnCurso;
	private TormentaPsionica tormenta;
	
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
		colocador = (ColocadorUnidadTerrestre) new ColocadorUnidadTerrestre();
		tormentaEnCurso = false;
		tormenta = null;
		
	}
		
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}

	public ColocadorUnidadTerrestre getColocador(){
		
		return (ColocadorUnidadTerrestre)colocador;
		
	}

	public Escudo getEscudo() {
	
		return escudo;
		
	}

	public int obtenerEnergia() {
	
		return energia.obtenerCantidad();
		
	}

	public void tormentaPsionica(int x, int y) {

		tormenta = new TormentaPsionica(mapa,x,y);

		if(energia.obtenerCantidad() >= tormenta.obtenerEnergiaNecesaria()) {
			tormenta.atacar();
			energia.disminuirEnergia(tormenta.obtenerEnergiaNecesaria());
			tormentaEnCurso = true;
		
		}

	}
	
	public void alucinacion(Unidad unidad){
		
		Alucinacion alucinacion = new Alucinacion();
		if(energia.obtenerCantidad() >= alucinacion.obtenerEnergiaNecesaria()){
			alucinacion.atacar(unidad);
			energia.disminuirEnergia(alucinacion.obtenerEnergiaNecesaria());
		}
		
	}
	
	public void recibirDanio (){
	
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		
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
	
	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		this.recibirDanio();
		vida.reestablecerDanioRecibido();
		
	}

	public void pasoTurno(){
		
		energia.aumentarEnergia(energiaPorTurno);
		if(tormentaEnCurso){
			tormenta.atacar();
			tormentaEnCurso = false;
		}
		
	}
	
}



