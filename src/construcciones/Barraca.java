package src.construcciones;

import src.ConstantesAlgoCraft;
import src.unidades.Marine;


public class Barraca extends Creadora {
	
	public Barraca(){
		super();
		setVida(ConstantesAlgoCraft.HP_BARRACA);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_BARRACA,0);
		
	}
	
	public Marine crearMarine(){
		
		Marine marine = new Marine();
		return marine;
		
	}
	
}
