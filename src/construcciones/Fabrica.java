package src.construcciones;

import src.ConstantesAlgoCraft;
import src.unidades.Golliat;

public class Fabrica extends Construccion {

	public Fabrica(){
		
		super();
		setVida(ConstantesAlgoCraft.HP_FABRICA);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_FABRICA,0);
		
	}
	
	
	public Golliat crearGolliat() {
		
		Golliat golliat = new Golliat();
		return golliat;
		
	}

}
