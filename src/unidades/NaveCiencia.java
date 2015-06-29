package src.unidades;

import src.Danio;
import src.Dinero;
import src.ReglaDeDanioTerran;
import src.Vida;
import src.mapa.Mapeable;


public class NaveCiencia extends Magica {

	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 10;
	private static final int TRANSPORTE = 0;
	private static final int VIDA = 200;
	private static final int VISION = 10;
	private static final int ENERGIA = 50;
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 225;
	
	public NaveCiencia (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Nave Ciencia";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		energia = new Energia(ENERGIA);
		energiaPorTurno = 10;
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

	public int obtenerEnergia() {
	
		return energia.obtenerCantidad(); 
		
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
		
		// No afecta a unidades amigas
		
	}
	
	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		
	}

	public void afectadoPorRadiacion(int danio){
		
		//supuesto: no afecta a naves
		
	}

	public void pasoTurno() {
		
		energia.aumentarEnergia(energiaPorTurno);
		
	}

}
