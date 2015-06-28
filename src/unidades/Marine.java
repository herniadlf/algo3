package src.unidades;

import src.Danio;
import src.Dinero;
import src.Escudo;
import src.ReglaDeDanioTerran;
import src.Vida;
import src.mapa.Mapeable;

public class Marine extends Unidad {

	private static final int DANIO_AIRE = 6;
	private static final int DANIO_TIERRA = 6;
	private static final int RANGO_ATAQUE = 4;
	private static final int SUMINISTRO = 1;
	private static final int TIEMPO_CREACION = 3;
	private static final int TRANSPORTE = 1;
	private static final int VIDA = 40;
	private static final int VISION= 7;
	private static final int COSTO_MINERALES = 50;
	private static final int COSTO_GAS = 0;
			
	public Marine (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Marine";
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
	
		return true;
		
	}

	public boolean esAereo() {
	
		return false;
	}

	public void atacarConEMP(int danio) {
		
		//no afecta a unidades amigas
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		Marine duplicado = new Marine();
		duplicado.vida = new Vida(1);
		duplicado.danio = new Danio(0, 0);
		return duplicado;
		
	}
		
	public Escudo getEscudo(){
		
		return null;
		
	}

}
