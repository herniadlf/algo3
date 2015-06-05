package src.construcciones;

import src.ConstantesAlgoCraft;
import src.mapa.FuenteDeGasVespeno;

public class Refineria extends Extractora{

	public Refineria( ){
		super();
		setFuente(new FuenteDeGasVespeno());
		setNombre("Refineria");
		setVida(ConstantesAlgoCraft.HP_REFINERIA);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_REFINERIA,0);
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_EXTRACTORES);
		setEdificioRequerido(new DepositoDeSuministros());
	}
}
