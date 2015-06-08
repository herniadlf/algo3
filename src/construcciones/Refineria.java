package src.construcciones;

import src.ConstantesAlgoCraft;
import src.mapa.FuenteDeGasVespeno;

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
	}
}
