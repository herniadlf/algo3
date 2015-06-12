package src.construcciones;

import src.ConstantesAlgoCraft;
import src.Escudo;
import src.Jugador;
import src.Turno;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.Mapa;

public class Asimilador extends Extractora {
	
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 450;
	private static final int ESCUDO = 450;
	private static final int TIEMPO = 6;
	private Escudo escudo;
	
	public Asimilador(){
		
		super();			
		setFuente(new FuenteDeGasVespeno());
		setNombre("Asimilador");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new Pilon());
		escudo = new Escudo(ESCUDO,this);
		
	}	
	public void recibirDanio (){	
		
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		
	}
	
	public void pasoTurno(Turno turno, Mapa mapa, Jugador jugadorActual){
		int minerales = jugadorActual.getDinero().getMinerales();
		int gas = jugadorActual.getDinero().getGasVespeno() + RECURSOS_POR_TURNO;
		jugadorActual.setDinero(minerales, gas);
	}
}
