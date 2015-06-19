package src.construcciones;

import excepciones.ExcepcionEdificioDestruido;
import excepciones.ExcepcionPosicionInvalida;
import src.ConstantesAlgoCraft;
import src.Escudo;
import src.Jugador;
import src.Turno;
import src.mapa.FuenteDeMinerales;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;

public class NexoMineral extends Extractora {
	
	private static final int COSTO_MINERALES = 50;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 250;
	private static final int ESCUDO = 250;
	private static final int TIEMPO = 4;
	private Escudo escudo;
	
	public NexoMineral() {
		
		super();
		setFuente(new FuenteDeMinerales());
		setNombre("Nexo Mineral");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new Pilon());
		escudo = new Escudo(ESCUDO,this);
		
	}

	public Escudo getEscudo(){
		
		return escudo;
		
	}
	
	public void recibirDanio () throws ExcepcionPosicionInvalida{
		
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		boolean estadoDeVidaFinalizado= vida.devolverEstadoDeVida();
		  if (estadoDeVidaFinalizado==true){
			 mapa.eliminarElementoTerrestreEnPosicion(super.getPosicionX(), super.getPosicionY());
			 }
	}
	
	public void pasoTurno(Turno turno, Mapa mapa, Jugador jugadorActual) throws ExcepcionEdificioDestruido{
		
		if (vida.devolverEstadoDeVida()){
			throw new ExcepcionEdificioDestruido();
		}
		int minerales = jugadorActual.getDinero().getMinerales()  + RECURSOS_POR_TURNO;
		int gas = jugadorActual.getDinero().getGasVespeno();
		jugadorActual.setDinero(minerales, gas);
		
	}
	
}
