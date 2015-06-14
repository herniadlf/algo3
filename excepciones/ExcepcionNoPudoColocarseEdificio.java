package excepciones;

public class ExcepcionNoPudoColocarseEdificio extends ExcepcionGeneral {

	public ExcepcionNoPudoColocarseEdificio(ExcepcionGeneral e) {
		super("No pudo colocarse edificio.");
		causa = e;
	}

}
