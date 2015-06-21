package src.construcciones;

import excepciones.ExcepcionEdificioDestruido;
import src.Jugador;
import src.ReglaDeDanioTerran;
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
		reglaDeDanio = new ReglaDeDanioTerran();
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
