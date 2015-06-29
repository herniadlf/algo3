package vista;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.unidades.Unidad;

public class ListaDeEntrenamiento {
	Juego controlador;
	InterfazPartida menuAnterior;
	JFrame frameActual;
	ArrayList<Unidad> unidades;
	
	public ListaDeEntrenamiento(InterfazPartida interfazAnterior) {
		menuAnterior = interfazAnterior;
		controlador = interfazAnterior.controladorJuego;
		frameActual = new JFrame();
		unidades = new ArrayList<Unidad>();
	}

	public void cargar(InterfazPrincipal ip) {
		frameActual.getContentPane().removeAll();
		frameActual.setJMenuBar(null);		
		frameActual.setTitle("Unidades bajo entrenamiento");
		
		armadoDeLista(controlador.getJugadorActual().getConstruccionesEnPie());
		
		int tamanioLista = unidades.size();
		
		JPanel nuevoPanel = new JPanel();
		JTextArea nuevaInformacion = new JTextArea("");
		nuevaInformacion.setFocusable(false);
		for (int i = 0 ; i < tamanioLista ; i++){
			Unidad auxiliar = unidades.get(i);
			int turnosRestantes = controlador.getTurno() - (auxiliar.getTurnoDeEntrenamiento()/2);
			if (turnosRestantes > 0){
				nuevaInformacion.append("\n" + auxiliar.getNombre() + " Turnos restantes: " + turnosRestantes);		
			}
		}
		
		nuevoPanel.add(nuevaInformacion);
		frameActual.getContentPane().add(nuevoPanel);
		frameActual.pack();
		frameActual.show();
		
	}

	private void armadoDeLista(ArrayList<Construccion> construccionesEnPie) {
		for (int i = 0; i < construccionesEnPie.size() ; i ++){
			Construccion edificio = construccionesEnPie.get(i);
			if (edificio instanceof Creadora){
				Creadora creadora = (Creadora) edificio;
				for (int j = 0; j < creadora.obtenerListaDeUnidadesAFabricar().size(); j++){
					unidades.add( creadora.obtenerListaDeUnidadesAFabricar().get(j) );
				}
			}
		}		
	}

	public void limpiar() {
		frameActual.getContentPane().removeAll();
		frameActual.setVisible(false);		
	}


}
