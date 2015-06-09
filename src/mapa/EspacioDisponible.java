package src.mapa;

public class EspacioDisponible implements Mapeable{

	public boolean esLoMismo(Mapeable aComparar){
		return (this.getNombre() == aComparar.getNombre());
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
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return true;
	}

}

