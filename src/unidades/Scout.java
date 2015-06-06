package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
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
	
	Escudo escudo;
	
	public Scout (){
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_SCOUT,ConstantesAlgoCraft.COSTO_GAS_SCOUT);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Scout");
		setRangoDeAtaques(RANGO_ATAQUE);
		setSuministros(SUMINISTRO);
		setTiempoDeCreacion(TIEMPO_CREACION);
		setTransporte(TRANSPORTE);
		setVida(new Vida(VIDA));
		setVision(VISION);
		escudo = new Escudo(ESCUDO,this); 
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
	
	@Override
	public void atacarEnTierra(Unidad unidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacarEnAire(Unidad unidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recibirDanio(int Danio) {
		// TODO Auto-generated method stub
		
	}

}
