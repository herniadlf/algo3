package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;

public class NaveTransporteProtoss extends Unidad {
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 5;
	private static final int TRANSPORTE = 8;// capacidad
	private static final int VIDA = 80;
	private static final int ESCUDO = 60;
	private static final int VISION = 8;
	
	Escudo escudo;
	
	public NaveTransporteProtoss() {		
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_TRANSPORTE_PROTOSS,0);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Nave Transporte");
		setRangoDeAtaques(RANGO_ATAQUE);
		setSuministros(SUMINISTRO);
		setTiempoDeCreacion(TIEMPO_CREACION);
		setTransporte(TRANSPORTE);
		setVida(new Vida(VIDA));
		setVision(VISION);	
		escudo = new Escudo (ESCUDO,this);
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
		
	
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		
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
