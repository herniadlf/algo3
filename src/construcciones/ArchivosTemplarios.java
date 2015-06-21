package src.construcciones;

import java.util.LinkedList;
import src.Escudo;
import src.ReglaDeDanioProtoss;
import src.unidades.*;

public class ArchivosTemplarios extends Creadora {
	
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 200;
	private static final int VIDA = 500;
	private static final int ESCUDO = 500;
	private static final int TIEMPO = 9;
	Escudo escudo;
	
	public ArchivosTemplarios(){
		
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new AltoTemplario());
		setNombre("Archivos Templarios");
		setTiempoDeConstruccion(TIEMPO);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setVida(VIDA);
		setEdificioRequerido(new PuertoEstelarProtoss());
		escudo = new Escudo (ESCUDO,this);
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
	}
}