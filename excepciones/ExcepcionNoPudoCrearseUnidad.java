package excepciones;

public class ExcepcionNoPudoCrearseUnidad extends ExcepcionGeneral {

	public ExcepcionNoPudoCrearseUnidad(ExcepcionGeneral e) {
		super (e.getMessage(),e);
	}

}
