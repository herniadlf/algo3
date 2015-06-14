package src.construcciones;

import src.ConstantesAlgoCraft;
import src.Jugador;
import src.Mineral;
import src.Turno;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;

public class CentroDeMineral extends Extractora {
	private static final int COSTO_MINERALES = 50;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 500;
	private static final int TIEMPO = 4;
	
	public CentroDeMineral( ){	
		
		super();
		setFuente(new FuenteDeMinerales());
		setNombre("Centro de Mineral");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new DepositoDeSuministros());
		
	}
		
	public void recibirDanio (){
			
		this.getVida().disminuirVidaPorDanio();
			
	}
	
	public void pasoTurno(Turno turno, Mapa mapa, Jugador jugadorActual){
		
		int minerales = jugadorActual.getDinero().getMinerales()  + RECURSOS_POR_TURNO;
		int gas = jugadorActual.getDinero().getGasVespeno();
		jugadorActual.setDinero(minerales, gas);
		
	}
	
}
