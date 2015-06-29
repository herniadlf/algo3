package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import src.Juego;
import src.unidades.Unidad;


public class MoverUnidad {

	Juego controladorJuego; 
	InterfazPartida menuAnterior;
	InterfazPrincipal iPrincipal;
	JFrame frameMover;
	
	public MoverUnidad(InterfazPartida interfazPartida) {
		menuAnterior = interfazPartida;
		frameMover = new JFrame();
	}

	public void cargar(InterfazPrincipal ip) {
		iPrincipal = ip;
		controladorJuego = ip.getJuego();
		ArrayList<Unidad> listaUnidades = controladorJuego.getJugadorActual().getUnidadesAlistadas();
		
		frameMover.getContentPane().removeAll();
		frameMover.setJMenuBar(null);
		frameMover.setTitle("Mover Unidad");	
		JPanel unPanel = new JPanel();
		GridLayout gL = new GridLayout(listaUnidades.size(),2);
		unPanel.setLayout(gL);
		for (int i = 0; i < listaUnidades.size();i++){
			Unidad auxiliar = listaUnidades.get(i);
			JLabel unLabel = new JLabel(auxiliar.getNombre() + "Posicion: (" + auxiliar.getPosicionX() + "," + 
													auxiliar.getPosicionY() +") ");
			unPanel.add(unLabel);
			unPanel.add(generarBoton(auxiliar));			
		}
		
		frameMover.getContentPane().add(unPanel);
		frameMover.pack();
		frameMover.setLocation(450,250);
		frameMover.show();
		
	}

	private JButton generarBoton(Unidad auxiliar) {
		JButton boton = new JButton("Seleccionar");
		boton.setSize(100, 50);
		boton.addActionListener(new EscuchaBotonSeleccionar(auxiliar));
		return boton;
		
	}

	private class EscuchaBotonSeleccionar implements ActionListener{
		Unidad aMover;
		
		public EscuchaBotonSeleccionar(Unidad u) {			
				aMover = u;			
		}

		public void actionPerformed(ActionEvent arg0) {
			CargarPosicion pp = new CargarPosicion(aMover);					
			pp.cargarY();
			pp.cargarX();			
		}
	}
	
	private class CargarPosicion{
		int posX=0,posY=0;
		JFrame nuevaFrame;
		Unidad aMover;
		
		private CargarPosicion(Unidad c){		
			nuevaFrame = new JFrame();
			aMover = c;
		}
		
		private void cargarX(){			
			nuevaFrame.getContentPane().removeAll();
			nuevaFrame.setJMenuBar(null);
			nuevaFrame.setTitle("Pedido Posicion");
			nuevaFrame.setSize(300, 100);	
			JPanel panelPosicion = new JPanel();
			JLabel posicionX = new JLabel("Ingrese posicion X: ");
			final JTextField ingresoPosX = new JTextField(3);
			ingresoPosX.setFocusable(true);
			ingresoPosX.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					posX = Integer.parseInt(ingresoPosX.getText());	
					cargarY();
				}
			});		
			panelPosicion.add(posicionX);
			panelPosicion.add(ingresoPosX);				
			nuevaFrame.getContentPane().add(panelPosicion);
			nuevaFrame.show();
		}
		private void cargarY(){
			nuevaFrame.getContentPane().removeAll();
			nuevaFrame.setJMenuBar(null);
			
			nuevaFrame.setTitle("Pedido Posicion");
			nuevaFrame.setSize(300, 100);	
			JPanel panelPosicion = new JPanel();
			JLabel posicionY = new JLabel("Ingrese posicion Y: ");
			final JTextField ingresoPosY = new JTextField(3);
			ingresoPosY.setFocusable(true);
			ingresoPosY.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					posY = Integer.parseInt(ingresoPosY.getText());
					nuevaFrame.setVisible(false);
									
						try {
							controladorJuego.getJugadorActual().moverUnidadAPosicion(aMover, posX, posY);
							JOptionPane.showMessageDialog(null, "Movimiento de " + aMover.getNombre() + " exitoso!");					
						
						} catch (ExcepcionNoPuedeMoverseUnidad
								| ExcepcionLaUnidadNoPertenceATuTropa e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							
						}
						limpiar();
						menuAnterior.cargar(iPrincipal);
				}
			});		
			panelPosicion.add(posicionY);
			panelPosicion.add(ingresoPosY);				
			nuevaFrame.getContentPane().add(panelPosicion);
			nuevaFrame.show();
		}
		private int getX(){ return posX; }
		private int getY(){ return posY; }
	}

	public void limpiar() {
		frameMover.getContentPane().removeAll();
		frameMover.setJMenuBar(null);
		frameMover.setVisible(false);
	}
	
	
	
}	