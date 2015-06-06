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
			
	public Marine (){
		setCosto(COSTO_MINERALES,0);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Marine");
		setRangoDeAtaques(RANGO_ATAQUE);
		setSuministros(SUMINISTRO);
		setTiempoDeCreacion(TIEMPO_CREACION);
		setTransporte(TRANSPORTE);
		setVida(new Vida(VIDA));
		setVision(VISION);
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
		
	public void atacarEnAire (Unidad unidad){
		
		unidad.recibirDanio((super.danio.getDanioAire()));
			
	} 
	
	public void recibirDanio (int danio){
		
			vida.recibirDanio(danio);
			
	}

	@Override
	public void atacarEnTierra(Unidad unidad) {
		
		unidad.recibirDanio((this.getDanio()).getDanioTierra());
		
		
	}

	@Override
	public boolean esPisable() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
