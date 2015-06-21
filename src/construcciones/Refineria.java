package src.construcciones;

import excepciones.ExcepcionEdificioDestruido;
import src.Jugador;
import src.ReglaDeDanioTerran;
import src.Turno;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.Mapa;

public class Refineria extends Extractora{
	
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 750;
	private static final int TIEMPO = 6;
	
	public Refineria( ){
		
		super();
		setFuente(new FuenteDeGasVespeno());
		setNombre("Refineria");
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
		int minerales = jugadorActual.getDinero().getMinerales();
		int gas = jugadorActual.getDinero().getGasVespeno() + RECURSOS_POR_TURNO;
		jugadorActual.setDinero(minerales, gas);
		
	}
	
}
