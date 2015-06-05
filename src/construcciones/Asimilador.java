package src.construcciones;

import src.ConstantesAlgoCraft;
import src.mapa.FuenteDeGasVespeno;

public class Asimilador extends Extractora {

		public Asimilador(FuenteDeGasVespeno fuente){
			super(fuente);
			setNombre("Asimilador");
			setVida(ConstantesAlgoCraft.HP_ASIMILADOR);
			setCosto(ConstantesAlgoCraft.COSTO_MINERALES_ASIMILADOR,0);
			setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_EXTRACTORES);
		}

}
