package src;

public class Dinero {
	
	private Mineral minerales;
	private GasVespeno gasVespeno;
		
	public int getMinerales() {
			
		return minerales.getCantidad();
			
	}
	
	public void setMinerales(int numero){
			
		minerales.setCantidad(numero);
			
	}
		
	public int getGasVespeno() {
			
		return gasVespeno.getCantidad();
			
	}
		
	public void setGasVespeno(int numero) {
			
		gasVespeno.setCantidad(numero);
			
	}
		
	public Dinero(int mins, int gas) {
			
		minerales = new Mineral(mins);
		gasVespeno = new GasVespeno(gas);
			
	}
	
	public void restar(Dinero costo) {
			
		minerales.setCantidad(this.getMinerales() - costo.getMinerales());		
		gasVespeno.setCantidad(this.getGasVespeno() - costo.getGasVespeno());
			
	}
		
}
