package excepciones;

public class ExcepcionErrorPasoDeTurno extends ExcepcionGeneral {

	public ExcepcionErrorPasoDeTurno(ExcepcionGeneral e) {
		super("Error en el cambio de turno");
		causa = e;
	}

}
