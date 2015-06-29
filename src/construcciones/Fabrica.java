package src.construcciones;

import java.util.LinkedList;
import src.ReglaDeDanioTerran;
import src.unidades.*;

public class Fabrica extends Creadora {
	
	private static final int COSTO_MINERALES = 200;
	private static final int COSTO_GAS = 100;
	private static final int VIDA = 1250;
	private static final int TIEMPO = 12;
	
	public Fabrica(){	
		
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new Golliat());
		setNombre("Fabrica");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);	
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new Barraca());
		reglaDeDanio = new ReglaDeDanioTerran();
		
	}
	
}
