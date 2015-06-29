package src;

public class Turno {
	
	int turno;
	
	public Turno() {	
		
		turno = 1;	
		
	}
	
	public void aumentarTurno(){
		
		this.turno= this.turno +1;
		
	}
	
	public int devolverTurnoActual(){
		
		return this.turno;
		
	}

}
