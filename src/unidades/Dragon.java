package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Escudo;
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
		entrenador = (EntrenadorUnidadVoladora) new EntrenadorUnidadVoladora();
		
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

	public Escudo getEscudo(){
		
		return escudo;
		
	}

	@Override
	public void atacarConEMP(int danio) {
		escudo.atacar(danio);
		
	}
	

}
