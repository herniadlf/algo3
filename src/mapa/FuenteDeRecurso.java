package src.mapa;

import src.Recurso;


public class FuenteDeRecurso implements Mapeable {
	
	protected String nombre;
	protected Recurso recurso;
	
	public Recurso getRecurso(){
		return recurso;
	}
	
	public FuenteDeRecurso(){
		
	}
	
	public FuenteDeRecurso colocarContenido(){
		return null;
	}

	public boolean esLoMismo(Mapeable aComparar){
		return (this.getNombre() == aComparar.getNombre());
	}
	
	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean esOcupable() {
		return false;
	}

	public boolean esTerrestre() {
		return true;
	}

	public boolean esAereo() {
		return false;
	}
	
}
