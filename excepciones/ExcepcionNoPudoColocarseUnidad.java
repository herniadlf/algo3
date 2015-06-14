package excepciones;

public class ExcepcionNoPudoColocarseUnidad extends ExcepcionGeneral {

	public ExcepcionNoPudoColocarseUnidad(ExcepcionGeneral e) {
		super("No pudo colocarse la unidad");
		causa = e;
	}

}
