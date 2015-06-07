package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;


public class Golliat extends Unidad {

	private static final int DANIO_AIRE = 10;
	private static final int DANIO_TIERRA = 12;
	private static final int RANGO_ATAQUE = 6;//es el rango de ataque en tierra.. pero tambien tiene rango en aire.:S
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 6;
	private static final int TRANSPORTE = 2;
	private static final int VIDA = 125;
	private static final int VISION= 8;	
		
	public Golliat (){
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_GOLLIAT,ConstantesAlgoCraft.COSTO_GAS_GOLLIAT);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Golliat");
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

	@Override
	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}
	public void atacarEnAire (Unidad unidad){
		int danio = this.getDanio().getDanioAire();
		unidad.seleccionarAtaqueAEnemigo(unidad, danio);

		/*if((this.getUnidadesAtacantes().size())==1){
			
			Unidad unidadARepeler = this.getUnidadesAtacantes().get(1);
			System.out.print(unidadARepeler.getVida().obtenerVida());
			
			
			
			
			
		}*/
		
		
	} 
	
	public void recibirDanio (){
		
	
		vida.recibirDanio();;
		
	}

	@Override
	public void atacarEnTierra(Unidad unidad){
		
		int danio = this.getDanio().getDanioTierra();
		unidad.seleccionarAtaqueAEnemigo(unidad, danio);
		
		/*if((this.getUnidadesAtacantes().size())==1){
			
			Unidad unidadARepeler = this.getUnidadesAtacantes().get(1);
			System.out.print(unidadARepeler.getVida().obtenerVida());
			
			
			
			
		}*/
			
			
			
			
			
	}


	@Override
	public boolean esPisable() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void seleccionarAtaqueAEnemigo(Unidad unidad,int danio) {
	
		unidad.getVida().aumentarDanioARecibir(danio);
		
	}
	

}
