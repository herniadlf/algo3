package src.unidades;


import src.Danio;
import src.Vida;
import src.mapa.Mapeable;


public class Zealot extends Unidad {


	
	public Zealot (){
		
		super.vida = new Vida (40);
		super.danio= new Danio();
		
		
		
	}
	
	@Override
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setearNombre() {
		super.nombre = "Marine";
		
	}
	@Override
	public void setearTransporte() {
		super.transporte=1;
		
	}
	@Override
	public void setearVision() {
		super.vision= 7;
		
	}
	@Override
	public void setearCosto() {
		super.costo= 50;
		
	}
	@Override
	public void setearTiempoDeCreacion() {
		super.tiempoDeCreacion=3;
		
	}
	@Override
	public void setearSuministros() {
		super.suministro=1;
		
	}
	@Override
	public void setearRangoDeAtaques() {
		super.rangoAtaque=4;
		
	}
	@Override
	public void setearVida() {
		super.vida= vida;
		
		
		
	}
	@Override
	public void setearDanio() {
		
		danio.setearDanioAire(6);
		danio.setearDanioTierra(6);
		
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.nombre;
	}

	@Override
	public int getTransporte() {
		// TODO Auto-generated method stub
		return super.transporte;
	}

	@Override
	public int getVision() {
		// TODO Auto-generated method stub
		return super.vision;
	}

	@Override
	public int getCosto() {
		// TODO Auto-generated method stub
		return super.costo;
	}

	@Override
	public int getTiempoDeCreacion() {
		// TODO Auto-generated method stub
		return super.tiempoDeCreacion;
	}

	@Override
	public int getSuministros() {
		// TODO Auto-generated method stub
		return super.suministro;
	}

	@Override
	public int getRangoDeAtaques() {
		// TODO Auto-generated method stub
		return super.rangoAtaque;
	}

	@Override
	public Vida getVida() {
		// TODO Auto-generated method stub
		return super.vida;
	}

	@Override
	public Danio getDanio() {
		// TODO Auto-generated method stub
		return super.danio;
	}
	
	public void atacarEnAire (Unidad unidad){
	
		unidad.recibirDanio((this.getDanio()).getDanioAire());
		
		
} 
	
	public void recibirDanio (int danio){
		
		
		if(danio> 60){
			this.vida.recibirDanio(danio);
			
			}
		
		
		
		
	}

	@Override
	public void atacarEnTierra(Unidad unidad) {
		// TODO Auto-generated method stub
		
		unidad.recibirDanio((this.getDanio()).getDanioTierra());
		
		
	}
	

}