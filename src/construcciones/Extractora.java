package src.construcciones;

import excepciones.ExcepcionEdificioDestruido;
import src.Dinero;
import src.Jugador;
import src.Turno;
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapa;

public abstract class Extractora extends Construccion {
	
	FuenteDeRecurso fuente;
		
	public FuenteDeRecurso getFuente(){
			
		return fuente;
		
	}
		
	public void setFuente(FuenteDeRecurso fuente){
			
		this.fuente = fuente; 
		
	}
		
	public Extractora(){
			
		super();
		setArquitecto(new ArquitectoDeExtractora());
			
	}
	
	public void pasoTurno(Turno turno, Mapa mapa, Jugador jugadorActual) throws ExcepcionEdificioDestruido{
		if (vida.devolverEstadoDeVida()){
			throw new ExcepcionEdificioDestruido();
		}
		Dinero extraido = fuente.extraer();
		int minerales = jugadorActual.getDinero().getMinerales() + extraido.getMinerales();
		int gas = jugadorActual.getDinero().getGasVespeno() + extraido.getGasVespeno();
		jugadorActual.setDinero(minerales, gas);
	}
		
}
