package src.unidades;


import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Escudo;
import src.Vida;
import src.mapa.Mapeable;


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
		entrenador = (EntrenadorUnidadTerrestre) new EntrenadorUnidadTerrestre();
		
	}		

		
	public Escudo getEscudo(){
		
		return escudo;
		
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

	public EntrenadorUnidadTerrestre getEntrenador(){
		return (EntrenadorUnidadTerrestre)entrenador;
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
		return true;
	}


	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}


	

}