package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.unidades.*;


public class Barraca extends Creadora {
	
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 1000;
	private static final int TIEMPO = 12;
	
	public Barraca(){
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new Marine());
		setNombre("Barraca");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new DepositoDeSuministros());
	}
	
	public Marine crearMarine(){
		
		Marine marine = new Marine();
		return marine;
		
	}
	
}
