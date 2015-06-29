package src;

public class Escudo{
	
	private int resistenciaTotal;
	private int resistenciaActual;
	private Atacable protegido;
	private static int REGENERACION_POR_TURNO = 10;
	
	public Escudo(int resistenciaTotal, Atacable protegido) {
		
		this.resistenciaTotal = resistenciaTotal; 
		this.protegido = protegido;
		resistenciaActual = this.resistenciaTotal;
		
	}
	
	public void atacar(int danio) {
		
		if(resistenciaActual - danio < 0){
			int vidaDaniada = danio - resistenciaActual;
			protegido.getVida().reestablecerDanioRecibido();
			protegido.getVida().aumentarDanioARecibir(vidaDaniada);
			protegido.getVida().disminuirVidaPorDanio();
			resistenciaActual = 0;
		}
		else {	
			resistenciaActual = resistenciaActual - danio;
		}
		
	}
	
	public int obtenerResistenciaActual(){
		
		return resistenciaActual;
		
	}
	
	public void pasoTurno(){
		
		
			if(resistenciaActual + REGENERACION_POR_TURNO <= resistenciaTotal){
				resistenciaActual = resistenciaActual + REGENERACION_POR_TURNO;
			}
			else {
				resistenciaActual = resistenciaTotal;	
			}
			
	}
	
}
