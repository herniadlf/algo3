package src.excepciones;

public class ExcepcionGeneral extends Exception {	

	public ExcepcionGeneral(String descripcion, Exception causa) {
		super(descripcion, causa);
	}
		
}
