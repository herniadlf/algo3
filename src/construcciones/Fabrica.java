package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.unidades.Golliat;

public class Fabrica extends Creadora {

	public Fabrica(){		
		unidadesCreables = new LinkedList();
		setNombre("Fabrica");
		setVida(ConstantesAlgoCraft.HP_FABRICA);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_FABRICA,0);	
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_FABRICA);
	}
	
	
	public Golliat crearGolliat() {
		
		Golliat golliat = new Golliat();
		return golliat;
		
	}

}
