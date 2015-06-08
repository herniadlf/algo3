package src.construcciones;

import src.ConstantesAlgoCraft;
import src.mapa.FuenteDeGasVespeno;

public class Asimilador extends Extractora {
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 450;
	private static final int ESCUDO = 450;
	private static final int TIEMPO = 6;
		public Asimilador(){
			super();			
			setFuente(new FuenteDeGasVespeno());
			setNombre("Asimilador");
			setVida(VIDA);
			setCosto(COSTO_MINERALES,COSTO_GAS);
			setTiempoDeConstruccion(TIEMPO);
			setEdificioRequerido(new Pilon());
			//escudo = new Escudo(ESCUDO,this);
		}

}
