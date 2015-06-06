package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
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
	
	Escudo escudo;
	
	public Dragon (){
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_DRAGON,ConstantesAlgoCraft.COSTO_GAS_DRAGON);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Dragon");
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
