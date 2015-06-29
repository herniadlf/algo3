package excepciones;

public class ExcepcionNoPuedeMoverseUnidad extends ExcepcionGeneral {

	public ExcepcionNoPuedeMoverseUnidad(Exception causa) {
		super(causa.getMessage(), causa);
	}

}
