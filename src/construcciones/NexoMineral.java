package src.construcciones;

import src.ConstantesAlgoCraft;
import src.mapa.FuenteDeMinerales;
import src.mapa.FuenteDeRecurso;

public class NexoMineral extends Extractora {

	public NexoMineral() {
		super();
		setFuente(new FuenteDeMinerales());
		setNombre("Nexo Mineral");
		setVida(ConstantesAlgoCraft.HP_NEXOMINERAL);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_NEXOMINERAL,0);
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_EXTRACTORES);
		setEdificioRequerido(new Pilon());
	}

}
