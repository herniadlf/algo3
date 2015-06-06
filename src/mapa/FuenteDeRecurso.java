package src.mapa;

import src.Recurso;


public class FuenteDeRecurso implements Mapeable {
	protected int posicionX;
	protected int posicionY;
	protected String nombre;
	protected Recurso recurso;
	
	public FuenteDeRecurso(){
		posicionX = 0;
		posicionY = 0;
		nombre = "Fuente de Recurso";
	}
	
	public FuenteDeRecurso colocarContenido(){
		return null;
	}

	public boolean esLoMismo(Mapeable aComparar){
		return (this.getNombre() == aComparar.getNombre());
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
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esPisable() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
