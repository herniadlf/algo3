package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Jugador;
import src.unidades.AltoTemplario;
import src.unidades.Dragon;
import src.unidades.NaveCiencia;
import src.unidades.NaveTransporteProtoss;
import src.unidades.Scout;
import src.unidades.Unidad;
import src.unidades.Zealot;

public class ListaUnidadesPropias {
	Jugador controlador;
	InterfazPartida menuAnterior;
	JFrame frameActual;
	
	public ListaUnidadesPropias(InterfazPartida interfazPartida) {
		menuAnterior = interfazPartida;
		controlador = interfazPartida.getControlador().getJugadorActual();
		frameActual = new JFrame();
	}

	public void cargar(InterfazPrincipal ip) {
		frameActual.getContentPane().removeAll();
		frameActual.setJMenuBar(null);		
		frameActual.setTitle("Unidades del jugador: " + controlador.getNombre());
		
		int tamanioLista = controlador.getUnidadesAlistadas().size(); 
		
		JPanel nuevoPanel = new JPanel();
		JTextArea nuevaInformacion = new JTextArea("");
		nuevaInformacion.setFocusable(false);
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
		
		if(auxiliar.getJugador().getRaza().getNombre() == "Protoss"){
			if(auxiliar.getNombre() == "Alto Templario"){
				return (auxiliar.getNombre()+" Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+" Escudo: "+auxiliar.getEscudo().obtenerResistenciaActual()+" Energia: "+((AltoTemplario)auxiliar).obtenerEnergia()+"\n");
			}
			else{
				return (auxiliar.getNombre()+" Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+" Escudo: "+auxiliar.getEscudo().obtenerResistenciaActual()+"\n");
			}
		}
		else{
			if(auxiliar.getNombre() == "Nave Ciencia"){
				return (auxiliar.getNombre()+" Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+" Energia: "+((NaveCiencia)auxiliar).obtenerEnergia()+"\n");
			}
			else{
				return (auxiliar.getNombre()+" Posicion: ("+auxiliar.getPosicionX()+","+auxiliar.getPosicionY()+") Vida: "+auxiliar.getVida().obtenerVida()+"\n");
			}
		}
		
	}

	public void limpiar() {
		frameActual.getContentPane().removeAll();
		frameActual.setJMenuBar(null);	
		frameActual.setVisible(false);
	}

}
