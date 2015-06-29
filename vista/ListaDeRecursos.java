package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import excepciones.ExcepcionFueraDelRangoDeVision;
import excepciones.ExcepcionPosicionInvalida;
import src.Juego;
import src.mapa.Mapeable;
import src.mapa.Posicion;
import src.mapa.Sector;


public class ListaDeRecursos {
	
	Juego controladorJuego;
	InterfazPartida menuAnterior;
	JFrame frameRecursos;
	
	public ListaDeRecursos(InterfazPartida interfazPartida) {
		controladorJuego = interfazPartida.getControlador();
		menuAnterior = interfazPartida;
		frameRecursos = new JFrame();
	}

	public void cargar(final InterfazPrincipal ip) throws ExcepcionPosicionInvalida {
		
		frameRecursos.getContentPane().removeAll();
		frameRecursos.setJMenuBar(null);		
		frameRecursos.setTitle("Recursos");	
		
		int tamanioLista = controladorJuego.getMapa().getListaRecursos().size(); 
		
		JPanel panelRecursos = new JPanel();
		
		JTextArea listaRecursos = new JTextArea("Recuerde que solo puede construir un extractor sobre una fuente de recursos. \n");
		listaRecursos.setFocusable(false);
		for (int i = 0; i < tamanioLista ; i++){
			Posicion pos = controladorJuego.getMapa().getListaRecursos().get(i);
			int x = pos.getX(), y = pos.getY();
			Mapeable auxiliar =  controladorJuego.getMapa().obtenerContenidoEnPosicion(x, y).getElementoEnTierra();
			try{
				verificacionRangoDeVision(controladorJuego.getMapa().obtenerContenidoEnPosicion(x, y));
				listaRecursos.append("\n" + auxiliar.getNombre() + " Posicion: (" + x + "," + y + ")");				
			} catch ( ExcepcionFueraDelRangoDeVision e) {}
		}		
		JButton avanzar = new JButton("Ok");
		avanzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				limpiar();
				menuAnterior.cargar(ip);								
			}
		});		
		panelRecursos.add(listaRecursos);
		panelRecursos.add(avanzar);
		frameRecursos.getContentPane().add(panelRecursos);
		frameRecursos.setSize(700, 500);
		frameRecursos.show();	
	}

	private void verificacionRangoDeVision(Sector buscado) throws ExcepcionFueraDelRangoDeVision {
		if (!controladorJuego.getJugadorActual().getRangoDeVision().contains(buscado)) {
			throw new ExcepcionFueraDelRangoDeVision("Fuera del rango de vision");
		}
		
	}

	public void limpiar() {
		frameRecursos.getContentPane().removeAll();
		frameRecursos.setJMenuBar(null);
		frameRecursos.setVisible(false);
	}

}
