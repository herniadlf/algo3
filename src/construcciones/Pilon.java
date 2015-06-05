package src.construcciones;

import src.ConstantesAlgoCraft;

public class Pilon extends NoExtractora {
	
	public Pilon(){
		super();
		setVida(ConstantesAlgoCraft.HP_PILON);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_PILON,0);
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_PILON);
		setEdificioRequerido(this);
	}
}
