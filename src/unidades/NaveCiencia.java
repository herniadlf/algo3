package src.unidades;

import java.util.ArrayList;

import src.Danio;
import src.Vida;
import src.mapa.Mapeable;


public class NaveCiencia extends Magica {

	private static int ENERGIA_POR_TURNO = 10;
	
	public NaveCiencia(){
		
		vida = new Vida(200);
		danio = new Danio();
		energia = new Energia(50);
		magias.add(new EMP());
		magias.add(new Radiacion());
		
	}
	
	public void pasoTurno(){
		
		energia.aumentarEnergia(ENERGIA_POR_TURNO);
		
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

	@Override
	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setearNombre() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearTransporte() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearVision() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearCosto() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearTiempoDeCreacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearSuministros() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearRangoDeAtaques() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearVida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setearDanio() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTransporte() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVision() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCosto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTiempoDeCreacion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSuministros() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRangoDeAtaques() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vida getVida() {
		
		return vida;
		
	}

	@Override
	public Danio getDanio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atacarEnTierra(Unidad unidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacarEnAire(Unidad unidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recibirDanio(int Danio) {
		// TODO Auto-generated method stub
		
	}


	public int obtenerEnergia() {
	
		return energia.obtenerCantidad(); 
	}


	public ArrayList<Magia> obtenerMagias() {
		
		return magias;
		
	}


	public void atacarConEMP(Unidad unidad) {
		
		Magia emp = magias.get(0);
		if(energia.obtenerCantidad() >= emp.obtenerEnergiaNecesaria()){
			emp.atacar(unidad);
			energia.disminuirEnergia(emp.obtenerEnergiaNecesaria());
		}
	}
	
	public void atacarConRadiacion(Unidad unidad) {
			
		Magia radiacion = magias.get(1);
		if(energia.obtenerCantidad() >= radiacion.obtenerEnergiaNecesaria()){
			radiacion.atacar(unidad);
			energia.disminuirEnergia(radiacion.obtenerEnergiaNecesaria());
		}	
		
	}

}
