package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
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
		colocador = (ColocadorUnidadTerrestre) new ColocadorUnidadTerrestre();
		
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
		
	public ColocadorUnidadTerrestre getColocador(){
		return (ColocadorUnidadTerrestre)colocador;
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
		return true;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void atacarConEMP(int danio) {
		//no afecta
		
	}

	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		
	}
	
	
	


}
