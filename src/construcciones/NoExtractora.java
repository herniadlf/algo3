package src.construcciones;

public abstract class NoExtractora extends Construccion {

		public NoExtractora(){
			super();
			setConstructor(new ArquitectoDeNoExtractora());
		}
}
