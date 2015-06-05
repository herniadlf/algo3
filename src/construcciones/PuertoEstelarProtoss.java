package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;

public class PuertoEstelarProtoss extends Creadora {
		
		public PuertoEstelarProtoss(){
			unidadesCreables = new LinkedList();
			setNombre("Puerto Estelar Protoss");
			setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_PUERTOS);
			setVida(ConstantesAlgoCraft.HP_PUERTOESTELAR_PROTOSS);
			setCosto(ConstantesAlgoCraft.COSTO_MINERALES_PUERTOESTELAR,ConstantesAlgoCraft.COSTO_GAS_PUERTOESTELAR);
						
		}
}
