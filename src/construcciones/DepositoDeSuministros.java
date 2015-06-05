package src.construcciones;

import src.ConstantesAlgoCraft;

public class DepositoDeSuministros extends NoExtractora{

	public DepositoDeSuministros(){
		super();
		setNombre("Deposito de Suministros");
		setVida(ConstantesAlgoCraft.HP_SUMINISTROS);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_SUMINISTROS,0);
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_SUMINISTROS);
		setEdificioRequerido(this);
	}
}
