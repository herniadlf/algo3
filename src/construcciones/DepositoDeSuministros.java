package src.construcciones;

import src.ConstantesAlgoCraft;

public class DepositoDeSuministros extends Construccion {

	public DepositoDeSuministros(){
		super();
		setVida(ConstantesAlgoCraft.HP_SUMINISTROS);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_SUMINISTROS,0);
	}
}
