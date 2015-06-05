package src.construcciones;

import src.ConstantesAlgoCraft;
import src.mapa.FuenteDeGasVespeno;

public class Asimilador extends Extractora {

		public Asimilador(){
			super();			
			setFuente(new FuenteDeGasVespeno());
			setNombre("Asimilador");
			setVida(ConstantesAlgoCraft.HP_ASIMILADOR);
			setCosto(ConstantesAlgoCraft.COSTO_MINERALES_ASIMILADOR,0);
			setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_EXTRACTORES);
			setEdificioRequerido(new Pilon());
		}

}
