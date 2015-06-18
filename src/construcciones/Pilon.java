package src.construcciones;

import excepciones.ExcepcionPosicionInvalida;
import src.ConstantesAlgoCraft;
import src.Escudo;

public class Pilon extends NoExtractora {
	
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 300;
	private static final int ESCUDO = 300;
	private static final int TIEMPO = 5;
	private static final int SUMINISTROS = 5;
	private Escudo escudo;
	
	public Pilon(){
		
		super();
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(this);
		capacidadDeSuministros = SUMINISTROS;
		escudo = new Escudo(ESCUDO,this);
		
	}
	
	public void recibirDanio () throws ExcepcionPosicionInvalida{
		
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		boolean estadoDeVidaFinalizado= vida.devolverEstadoDeVida();
		  if (estadoDeVidaFinalizado==true){
			 mapa.eliminarElementoTerrestreEnPosicion(super.getPosicionX(), super.getPosicionY());
			 }
	}
	
}
