package src.mapa;

public class Pasto implements Mapeable{

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

}

