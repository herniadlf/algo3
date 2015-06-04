package src.construcciones;

import src.ConstantesAlgoCraft;
import src.unidades.Espectro;

public class PuertoEstelarTerran extends Construccion {

	public PuertoEstelarTerran(){
		
		super();
		setVida(ConstantesAlgoCraft.HP_PUERTOESTELAR_TERRAN);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_PUERTOESTELAR,0);
	
	}
	
	public Espectro crearEspectro() {
		
		Espectro espectro = new Espectro();
		return espectro;
		
	}

}
