package src;

public class Jugador {
	
	private String nombre;
	public String getNombre(){return nombre;}
	public void setNombre(String n){ nombre = n; }
	
	private String color;
	public String getColor(){return color;}
	public void setColor(String c){ color = c;	}
	
	private Raza raza;
	public void setRaza(Raza r){ raza = r; }
	
	private int unidades;
	public int getUnidades(){ return unidades; }
	public void setUnidades(int numero){ unidades = numero; }
	
	private Dinero dineroJugador;
	public Dinero getDinero(){ return dineroJugador; }
	public void setDinero(int minerales,int gasVespeno){ 
		dineroJugador = new Dinero(minerales,gasVespeno);
	}
	
	public Jugador(String nombre, String color, Raza raza){
		setNombre(nombre);
		setColor(color);
		setRaza(raza);
		setUnidades(10);
		setDinero(800,400);
	}
	
	public Jugador(){
		setUnidades(10);
		setDinero(800,400);
	}
		
	//for testing
	public void prepararParaPrueba() {
		setDinero(400,400);
		setUnidades(100);
		setRaza(new Protoss());
		setColor("rojo");
		setNombre("jorge");
	}
	public void construir(Construccion edificio) {
		int cantidadMineral = dineroJugador.getMinerales();
		cantidadMineral = cantidadMineral - edificio.getCosto().getMinerales();
		dineroJugador.setMinerales(cantidadMineral);		
	}
	
}
