package src.construcciones;

import src.ConstantesAlgoCraft;
import src.Mineral;
import src.mapa.FuenteDeMinerales;

public class CentroDeMineral extends Extractora {

		public CentroDeMineral( ){	
			super();
			setFuente(new FuenteDeMinerales());
			setNombre("Centro de Mineral");
			setVida(ConstantesAlgoCraft.HP_CENTROMINERAL);
			setCosto(ConstantesAlgoCraft.COSTO_MINERALES_CENTROMINERAL,0);
			setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_EXTRACTORES);
			setEdificioRequerido(new DepositoDeSuministros());
		}

}
