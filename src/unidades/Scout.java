package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Escudo;
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
		colocador = (ColocadorUnidadVoladora) new ColocadorUnidadVoladora();
		
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
		
	
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		
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
		
		escudo.atacar(danio);
		
	}
	
	public Escudo getEscudo(){
		
		return escudo;
		
	}
	
}



