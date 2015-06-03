package excepciones;

public class ExcepcionGeneral extends Exception {

		String descripcion;
		public String getDescripcion(){ 
			return descripcion;
		}
		public void setDescripcion(String descrip){
			descripcion = descrip;
		}

		public ExcepcionGeneral(String descrip){
			setDescripcion(descrip);
		}
		
		public String getMensaje(){
			return getDescripcion();
		}
}
