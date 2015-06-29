package excepciones;

public class ExcepcionNoSePuedeTransportar extends ExcepcionGeneral {

	public ExcepcionNoSePuedeTransportar(Exception causa) {
		super(causa.getMessage(), causa);
	}

}
