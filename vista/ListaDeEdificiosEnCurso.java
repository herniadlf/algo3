package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.Juego;
import src.construcciones.Construccion;

public class ListaDeEdificiosEnCurso {
	
	Juego controladorJuego;
	InterfazPartida menuAnterior;
	JFrame nuevaFrame;
	
	public ListaDeEdificiosEnCurso(InterfazPartida interfazPartida) {
		controladorJuego = interfazPartida.getControlador();
		menuAnterior = interfazPartida;
		nuevaFrame = new JFrame();
	}

	public void cargar(InterfazPrincipal interfazPrincipal) {
		
		nuevaFrame.getContentPane().removeAll();
		nuevaFrame.setJMenuBar(null);
		nuevaFrame.setTitle("Edificios en construccion.");	
		
		int tamanioLista = controladorJuego.getJugadorActual().obtenerConstruccionesEnCamino().size(); 	
		
		JPanel unPanel = new JPanel();
		GridLayout gL = new GridLayout(tamanioLista,1);
		gL.setHgap(0);
		gL.setVgap(0);
		unPanel.setLayout(gL);
		for ( int i = 0; i < tamanioLista; i++){
			Construccion auxiliar = controladorJuego.getJugadorActual().obtenerConstruccionesEnCamino().get(i);
			if (obtenerTurnosRestantes(auxiliar) > 0){
				JLabel unLabel = new JLabel(auxiliar.getNombre()+"  Posicion: ("+auxiliar.getPosicionX()+","+
												auxiliar.getPosicionY()+")  Turnos Restantes: "+
													obtenerTurnosRestantes(auxiliar)+"\n");	
				unPanel.add(unLabel);
			}
		}
		JButton avanzar = new JButton("Ok");
		avanzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {						
				limpiar();		
			}
		});
		
		unPanel.add(avanzar);
		nuevaFrame.getContentPane().add(unPanel);
		nuevaFrame.pack();
		nuevaFrame.show();	
	}

	private int obtenerTurnosRestantes(Construccion auxiliar) {
		int turnosPasados = controladorJuego.getTurno() - (auxiliar.getTurnoInicioDeConstruccion()/2);
		
		return (auxiliar.getTiempoDeConstruccion() - turnosPasados);
	}

	public void limpiar() {
		nuevaFrame.getContentPane().removeAll();
		nuevaFrame.setJMenuBar(null);
		nuevaFrame.setVisible(false);
	}	
	
}
