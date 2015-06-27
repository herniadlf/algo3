package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Jugador;
import src.unidades.Unidad;
import src.unidades.Zealot;

public class ListaUnidadesPropias {
	Jugador controlador;
	MenuUnidades menuAnterior;
	JFrame frameActual;
	
	public ListaUnidadesPropias(MenuUnidades menuUnidades) {
		menuAnterior = menuUnidades;
		controlador = menuUnidades.jugadorActual;
		frameActual = new JFrame();
	}

	public void cargar(InterfazPrincipal ip) {
		frameActual.getContentPane().removeAll();
		frameActual.setJMenuBar(null);		
		frameActual.setTitle("Unidades del jugador: " + controlador.getNombre());
		
		int tamanioLista = controlador.getUnidadesAlistadas().size(); 
		
		JPanel nuevoPanel = new JPanel();
		JTextArea nuevaInformacion = new JTextArea("");
		for (int i = 0 ; i < tamanioLista ; i++){
			Unidad auxiliar = controlador.getUnidadesAlistadas().get(i);
			nuevaInformacion.append(generarTexto(auxiliar));
		}
		nuevoPanel.add(nuevaInformacion);
		frameActual.getContentPane().add(nuevoPanel);
		frameActual.pack();
		frameActual.show();
	}

	private String generarTexto(Unidad auxiliar) {

		if(auxiliar.getJugador().getRaza().getNombre()== "Protoss"){
	
			return (auxiliar.getNombre()+"Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+ " Escudo: "+((Zealot)auxiliar).getEscudo().obtenerResistenciaActual()+"\n");
		
		}
		else{
		
		return (auxiliar.getNombre()+"Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+"\n");
		
		}
	}

	public void limpiar() {
		frameActual.getContentPane().removeAll();
		frameActual.setJMenuBar(null);	
		frameActual.setVisible(false);
	}

}
