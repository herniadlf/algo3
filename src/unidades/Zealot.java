package src.unidades;


import src.Danio;
import src.Vida;
import src.mapa.Mapeable;


public class Zealot extends Unidad {

	Escudo escudo;
	
	public Zealot (){
		
		vida = new Vida (100);
		danio= new Danio();	
		escudo = new Escudo(60, this);
		
	}
	
	public Escudo getEscudo(){
		
		return escudo;
		
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
		
		nombre = "Zealot";
		
	}
	@Override
	public void setearTransporte() {
		
		super.transporte = 2;
		
	}
	@Override
	public void setearVision() {
		
		vision = 7;
		
	}
	@Override
	public void setearCosto() {
		
		costo = 100;
		
	}
	@Override
	public void setearTiempoDeCreacion() {
		
		tiempoDeCreacion = 4;
		
	}
	@Override
	public void setearSuministros() {
		
		suministro = 2;
		
	}
	@Override
	public void setearRangoDeAtaques() {
		
		rangoAtaque = 1;
		
	}
	@Override
	public void setearVida() {
		
		
	}
	@Override
	public void setearDanio() {
		
		danio.setearDanioTierra(8);
		
	}

	@Override
	public String getNombre() {
	
		return nombre;
		
	}

	@Override
	public int getTransporte() {
		
		return transporte;
		
	}

	@Override
	public int getVision() {
		
		return vision;
		
	}

	@Override
	public int getCosto() {
		
		return costo;
		
	}

	@Override
	public int getTiempoDeCreacion() {
		
		return tiempoDeCreacion;
		
	}

	@Override
	public int getSuministros() {
		
		return suministro;
		
	}

	@Override
	public int getRangoDeAtaques() {
	
		return rangoAtaque;
		
	}

	@Override
	public Vida getVida() {
		
		return vida;
		
	}

	@Override
	public Danio getDanio() {
		
		return danio;
		
	}
	
	public void atacarEnAire (Unidad unidad){
	
		unidad.recibirDanio((this.getDanio()).getDanioAire());
		
	} 
	
	public void recibirDanio (int danio){
		
		escudo.atacar(danio);
		
	}

	@Override
	public void atacarEnTierra(Unidad unidad) {
		
		unidad.recibirDanio((this.getDanio()).getDanioTierra());
		
	}
	

}