package src.construcciones;

import src.ConstantesAlgoCraft;
import src.Mineral;
import src.mapa.FuenteDeMinerales;

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

}
