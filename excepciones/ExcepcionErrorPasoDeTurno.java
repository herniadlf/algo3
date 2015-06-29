package excepciones;

public class ExcepcionErrorPasoDeTurno extends ExcepcionGeneral {

	public ExcepcionErrorPasoDeTurno(ExcepcionGeneral e) {
		super(e.getMessage(),e);
	}

}
