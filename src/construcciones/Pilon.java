package src.construcciones;

import src.Escudo;
import src.ReglaDeDanioProtoss;

public class Pilon extends NoExtractora {
	
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 300;
	private static final int ESCUDO = 300;
	private static final int TIEMPO = 5;
	private static final int SUMINISTROS = 5;
	private Escudo escudo;
	
	public Pilon(){
		
		super();
		setVida(VIDA);
		setNombre("Pilon");
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(this);
		capacidadDeSuministros = SUMINISTROS;
		escudo = new Escudo(ESCUDO,this);
		reglaDeDanio = new ReglaDeDanioProtoss(escudo);
		
	}
	
	public Escudo getEscudo(){
		
		return escudo;
		
	}
		
		
}
