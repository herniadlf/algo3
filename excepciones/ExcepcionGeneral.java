package excepciones;

public class ExcepcionGeneral extends Exception {

		String descripcion;
		
		public String getDescripcion() { 
			
			return descripcion;
			
		}
		
		public void setDescripcion(String descripcion) {
			
			this.descripcion = descripcion;
		}

		public ExcepcionGeneral(String descripcion) {
			
			setDescripcion(descripcion);
			
		}
		
		public String getMensaje() {
			
			return getDescripcion();
			
		}
		
}
