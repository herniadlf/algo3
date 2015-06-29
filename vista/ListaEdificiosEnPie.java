package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Juego;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.unidades.AltoTemplario;
import src.unidades.NaveCiencia;

public class ListaEdificiosEnPie {

	Juego controladorJuego;
	InterfazPartida menuAnterior;
	JFrame frameEdificiosEnPie ;
	
	public ListaEdificiosEnPie(InterfazPartida interfazPartida) {
		controladorJuego = interfazPartida.getControlador();
		menuAnterior = interfazPartida;
		frameEdificiosEnPie = new JFrame();
	}
	public void cargar(final InterfazPrincipal ip) {
	
		frameEdificiosEnPie.getContentPane().removeAll();
		frameEdificiosEnPie.setJMenuBar(null);
		frameEdificiosEnPie.setTitle("Construcciones del Jugador: " + controladorJuego.getJugadorActual().getNombre() + " en pie");	
		
		JPanel panelEdificios = new JPanel();
		JTextArea lista = new JTextArea("");
		lista.setFocusable(false);
		for (int i = 0; i < controladorJuego.getJugadorActual().getConstruccionesEnPie().size() ; i++){
			Construccion auxiliar = controladorJuego.getJugadorActual().getConstruccionesEnPie().get(i);
			lista.append("\n" + generarTexto(auxiliar));
		}
		JButton avanzar = new JButton("Ok");
		avanzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiar();	
			}
		});
		panelEdificios.add(lista);
		panelEdificios.add(avanzar);
		frameEdificiosEnPie.getContentPane().add(panelEdificios);
		frameEdificiosEnPie.setSize(700, 500);
		frameEdificiosEnPie.show();
	}
	
	private String generarTexto(Construccion auxiliar) {
		
		if(auxiliar.getNombre()== "Nexo Mineral" ||
		   auxiliar.getNombre()== "Pilon" ||
		   auxiliar.getNombre()== "Asimilador" ||
		   auxiliar.getNombre()== "Acceso" ||
		   auxiliar.getNombre()== "Puerto Estelar Protoss" ||
		   auxiliar.getNombre()== "Archivos Templarios"){
			
			return (auxiliar.getNombre()+" Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+" Escudo: "+ auxiliar.getEscudo().obtenerResistenciaActual()+"\n");
			
		}
			else{
				
				return (auxiliar.getNombre()+" Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+"\n");
			}
		
	}
	
	public void limpiar() {
		frameEdificiosEnPie.getContentPane().removeAll();
		frameEdificiosEnPie.setJMenuBar(null);
		frameEdificiosEnPie.setVisible(false);
	}
	

}
