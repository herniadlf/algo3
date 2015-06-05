package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.unidades.Marine;


public class Barraca extends Creadora {
	
	public Barraca(){
		super();
		unidadesCreables = new LinkedList();
		setNombre("Barraca");
		setVida(ConstantesAlgoCraft.HP_BARRACA);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_BARRACA,0);
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_BARACA);
		setEdificioRequerido(new DepositoDeSuministros());
	}
	
	public Marine crearMarine(){
		
		Marine marine = new Marine();
		return marine;
		
	}
	
}
