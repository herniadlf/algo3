package src.unidades;

import excepciones.ExcepcionPosicionInvalida;
import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.Vida;
import src.mapa.Mapeable;

public class Scout extends Unidad {

	private static final int DANIO_AIRE = 14;
	private static final int DANIO_TIERRA = 8;
	private static final int RANGO_ATAQUE = 4;
	private static final int SUMINISTRO = 3;
	private static final int TIEMPO_CREACION = 8;
	private static final int TRANSPORTE = 0;
	private static final int VIDA = 150;
	private static final int ESCUDO = 100;
	private static final int VISION= 7;
	private static final int COSTO_MINERALES = 300;
	private static final int COSTO_GAS = 150;
	Escudo escudo;
	
	public Scout (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Scout";
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
		
		escudo.atacar(danio);
		
	}
	
	public Escudo getEscudo(){
		
		return escudo;
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		Scout duplicado = new Scout();
		duplicado.vida = new Vida(1);
		duplicado.escudo = new Escudo(0, duplicado);
		duplicado.danio = new Danio(0, 0);
		return duplicado;
		
	}
		

	
}



