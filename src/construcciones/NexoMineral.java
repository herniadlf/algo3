package src.construcciones;

import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.mapa.FuenteDeMinerales;


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
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
	}

	public Escudo getEscudo(){
		
		return escudo;
		
	}
	
}
