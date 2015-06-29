package src.excepciones;

public class ExcepcionNoPudoColocarseEdificio extends ExcepcionGeneral {

	public ExcepcionNoPudoColocarseEdificio(ExcepcionGeneral e) {
		super(e.getMessage(),e);
	}

}
