package src.unidades;

import excepciones.ExcepcionPosicionInvalida;
import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.Vida;
import src.mapa.Mapeable;

public class Dragon extends Unidad {

	private static final int DANIO_AIRE = 20;
	private static final int DANIO_TIERRA = 20;
	private static final int RANGO_ATAQUE = 4;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 5;
	private static final int TRANSPORTE = 4;
	private static final int VIDA = 100;
	private static final int ESCUDO = 80;
	private static final int VISION= 8;
	private static final int COSTO_MINERALES = 125;
	private static final int COSTO_GAS = 50;
	Escudo escudo;
	
	public Dragon (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Dragon";
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

	public Escudo getEscudo(){
		
		return escudo;
		
	}

	public void atacarConEMP(int danio) {
		
		escudo.atacar(danio);
		
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

}
