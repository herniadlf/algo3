package vista;


import java.util.ArrayList;

import javax.swing.JComboBox;

import src.Escudo;
import src.Jugador;
import src.construcciones.Construccion;
import src.unidades.Magica;
import src.unidades.Unidad;

public abstract class CargarInformacionUnidades {
	
	
	public CargarInformacionUnidades (){}
	
	
	
	
	
	
	public void caragarListasDesplegablesUnidades (JComboBox menuDesplegable, ArrayList<Unidad>unidadesIndexadas,
			ArrayList<Unidad> unidades){
		int i=0;
		while (unidades.size()>i){
			menuDesplegable.addItem(Integer.toString(i)+"."+ unidades.get(i).getNombre());
			unidadesIndexadas.add (unidades.get(i));
					i++;
				}
		}
	
	//--------------------------------------------------------------------------------------------------
	
	public void cargarListaDesplegablesUnidadesMagicas(JComboBox menuDesplegable, ArrayList<Unidad>unidadesMagicas,
			Jugador jugador	){
			
			int i=0;
			int j=0;
			while (jugador.getUnidadesAlistadas().size()>i){
				ArrayList<Unidad>unidades = jugador.getUnidadesAlistadas();
				
				if (unidades.get(i) instanceof Magica){
					
					menuDesplegable.addItem(Integer.toString(j)+"."+ unidades.get(i).getNombre());
					unidadesMagicas.add(unidades.get(i));
					j++;
					}
				i++;
				}
		} 
	
//------------------------------------------------------------------------------------------------	
	
	public void cargarListaDesplegableConstrucciones (JComboBox menuDesplegable, ArrayList<Construccion>construccionesIndexadas,
			ArrayList<Construccion> construccion){
		int i=0;
		while (construccion.size()>i){
			menuDesplegable.addItem(Integer.toString(i)+"."+ construccion.get(i).getNombre());
			construccionesIndexadas.add (construccion.get(i));
					i++;
				}
		
		
}
	
	//----------------------------------------------------------------------------------------------------
	
	public final  int obtenerIndiceDeElemento (int posicion, String cadena, String indiceBuscado){
	
		posicion= cadena.indexOf(".");
		int i=0;
		while (i < posicion){
			
			indiceBuscado= indiceBuscado+ (cadena.charAt(i));
			i++;
			}
	
		
		return (Integer.parseInt(indiceBuscado));
		
		}
	
	
	
	public abstract void agregarAccionesListasDesplegables();
	
	public abstract void cargarJugadores ();
	
	public abstract void limpiar();
	
	
	

}
