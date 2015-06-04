package src.construcciones;

import src.ConstantesAlgoCraft;

public class Refineria extends Construccion{

	public Refineria(){
		super();
		setVida(ConstantesAlgoCraft.HP_REFINERIA);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_REFINERIA,0);
	}
}
