package excepciones;

public class ExcepcionNoPudoColocarseUnidad extends ExcepcionGeneral {

	public ExcepcionNoPudoColocarseUnidad(ExcepcionGeneral e) {
		super(e.getMessage(),e);
	}

}
