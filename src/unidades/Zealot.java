package src.unidades;


import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
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
	
	Escudo escudo;
	
	public Zealot (){
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_ZEALOT,0);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Zealot");
		setRangoDeAtaques(RANGO_ATAQUE);
		setSuministros(SUMINISTRO);
		setTiempoDeCreacion(TIEMPO_CREACION);
		setTransporte(TRANSPORTE);
		setVida(new Vida(VIDA));
		setVision(VISION);
		escudo = new Escudo(ESCUDO,this); 
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

	
	public void atacarEnAire (Unidad unidad){
	
		unidad.recibirDanio((this.getDanio()).getDanioAire());
		
	} 
	
	public void recibirDanio (int danio){
		
		escudo.atacar(danio);
		
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