package src.unidades;

import java.util.ArrayList;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;


public class NaveTransporteTerran extends DeTransporte {
	
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 5;
	private static final int TRANSPORTE = 8;// capacidad
	private static final int VIDA = 150;
	private static final int VISION = 8;
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 100;
	
	public NaveTransporteTerran() {
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Nave Transporte Terran";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		colocador = (ColocadorUnidadVoladora) new ColocadorUnidadVoladora();
		unidadesAbordo = new ArrayList<Unidad>();
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

	public ColocadorUnidadVoladora getColocador(){
		return (ColocadorUnidadVoladora)colocador;
	}
	
	
	public void recibirDanio (){
		
	
		vida.disminuirVidaPorDanio();;
		
	}

	


	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void atacarConEMP(int danio) {
		// no afecta
		
	}

	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		
	}

	@Override
	public void pasoTurno() {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
