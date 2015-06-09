package src.mapa;

import src.Recurso;


public class FuenteDeRecurso implements Mapeable {
	protected String nombre;
	protected Recurso recurso;
	
	public FuenteDeRecurso(){
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
		return nombre;
	}

	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
