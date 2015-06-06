package src.unidades;

import src.Danio;
import src.Vida;
import src.mapa.Mapeable;

public class Marine extends Unidad {

	
	
	public Marine (){
		
		super.vida = new Vida (40);
		super.danio= new Danio();
		this.setearDanio();
		
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
		
		nombre = "Marine";
		
	}
	@Override
	public void setearTransporte() {
		
		transporte = 1;
		
	}
	@Override
	public void setearVision() {
		
		vision= 7;
		
	}
	@Override
	public void setearCosto() {
		
		costo= 50;
		
	}
	@Override
	public void setearTiempoDeCreacion() {
		
		tiempoDeCreacion = 3;
		
	}
	@Override
	public void setearSuministros() {
		
		suministro = 1;
		
	}
	@Override
	public void setearRangoDeAtaques() {
		
		rangoAtaque = 4;
		
	}
	@Override
	public void setearVida() {
		
		//vida = vida;
		
		
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
	
		return super.vida;
	}

	@Override
	public Danio getDanio() {
		
		return danio;
	}
	
	public void atacarEnAire (Unidad unidad){
		
		unidad.recibirDanio((super.danio.getDanioAire()));
		
		
} 
	
	public void recibirDanio (int danio){
		
			vida.recibirDanio(danio);
			
	}

	@Override
	public void atacarEnTierra(Unidad unidad) {
		
		unidad.recibirDanio((this.getDanio()).getDanioTierra());
		
		
	}
	

}
