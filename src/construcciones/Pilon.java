package src.construcciones;

import src.ConstantesAlgoCraft;
import src.Escudo;

public class Pilon extends NoExtractora {
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 300;
	private static final int ESCUDO = 300;
	private static final int TIEMPO = 5;
	private static final int SUMINISTROS = 5;
	
	Escudo escudo;
	
	public Pilon(){
		super();
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(this);
		capacidadDeSuministros = SUMINISTROS;
		//escudo = new Escudo(ESCUDO,this);
	}
}
