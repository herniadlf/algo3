package src.construcciones;

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
	
	public void recibirDanio (){
		
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		
	}
	
	public void pasoTurno(Turno turno, Mapa mapa, Jugador jugadorActual){
		
		int minerales = jugadorActual.getDinero().getMinerales()  + RECURSOS_POR_TURNO;
		int gas = jugadorActual.getDinero().getGasVespeno();
		jugadorActual.setDinero(minerales, gas);
		
	}
	
}
