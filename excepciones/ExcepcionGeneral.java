package excepciones;

public class ExcepcionGeneral extends Exception {
	
	String descripcion;
	ExcepcionGeneral causa;
			
	public ExcepcionGeneral getCausa(){
		return causa;
	}
	
	public String getDescripcion() { 			
		return descripcion;			
	}
		
	public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;		
	}

	public ExcepcionGeneral(String descripcion2 ) {			
		descripcion = "Error";		
		causa = this;
	}
		
	public String getMensaje() {			
		return getDescripcion();			
	}
		
}
