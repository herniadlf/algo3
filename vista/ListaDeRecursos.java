package vista;

import java.awt.Color;
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
import src.mapa.FuenteDeRecurso;
import src.mapa.Mapeable;
import src.mapa.Posicion;
import src.mapa.Sector;


public class ListaDeRecursos {
	
	Juego controladorJuego;
	InterfazPartida iPartida;
	
	public ListaDeRecursos(Juego controlador, InterfazPartida interfazPartida) {
		controladorJuego = controlador;
		iPartida = interfazPartida;
	}

	public void cargar(InterfazPrincipal ip) throws ExcepcionPosicionInvalida {
		JFrame frameRecursos = new JFrame();
		frameRecursos.getContentPane().removeAll();
		frameRecursos.setJMenuBar(null);		
		frameRecursos.setTitle("Recursos");	
		
		int tamanioLista = controladorJuego.getMapa().getListaRecursos().size(); 
		
		JPanel panelRecursos = new JPanel();
		GridLayout gL = new GridLayout(tamanioLista, 1);
		panelRecursos.setLayout(gL);
		
		JLabel listaRecursos = new JLabel("Recuerde que solo puede construir un extractor sobre una fuente de recursos. \n");
		panelRecursos.add(listaRecursos);
		for (int i = 0; i < tamanioLista ; i++){
			Posicion pos = controladorJuego.getMapa().getListaRecursos().get(i);
			int x = pos.getX(), y = pos.getY();
			Mapeable auxiliar =  controladorJuego.getMapa().obtenerContenidoEnPosicion(x, y).getElementoEnTierra();
			try{
				verificacionRangoDeVision(controladorJuego.getMapa().obtenerContenidoEnPosicion(x, y));
				JLabel unLabel = new JLabel("\n" + auxiliar.getNombre() + " Posicion: (" + x + "," + y + ")");
				panelRecursos.add(unLabel);
			} catch ( ExcepcionFueraDelRangoDeVision e) {}
		}		
		JButton avanzar = new JButton("Ok");
		avanzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				iPartida.cargar(ip);		
				frameRecursos.getContentPane().removeAll();
				frameRecursos.setJMenuBar(null);
				frameRecursos.setVisible(false);			
			}
		});		
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

}
