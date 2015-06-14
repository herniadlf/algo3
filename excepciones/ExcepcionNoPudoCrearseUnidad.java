package excepciones;

public class ExcepcionNoPudoCrearseUnidad extends ExcepcionGeneral {

	public ExcepcionNoPudoCrearseUnidad(ExcepcionGeneral e) {
		super ("No pudo crearse la unidad");
		causa = e;
	}

}
