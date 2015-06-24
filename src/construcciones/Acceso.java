package src.construcciones;

import java.util.LinkedList;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.unidades.*;

public class Acceso extends Creadora {
		
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 500;
	private static final int ESCUDO = 500;
	private static final int TIEMPO = 8;
	private Escudo escudo;
	
	public Acceso (){
			
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new Dragon());
		unidadesCreables.add(new Zealot());
		setNombre("Acceso");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new Pilon());
		escudo = new Escudo(ESCUDO,this);
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
		
	}
		
	public Escudo getEscudo(){
			
		return escudo;
		
	}
					
}
