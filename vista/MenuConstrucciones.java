package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import excepciones.ExcepcionNoPudoColocarseEdificio;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;

public class MenuConstrucciones {
	Jugador jugadorActual;
	InterfazPartida interfazPartida;	
	Juego controladorJuego;
	InterfazPrincipal interfazPrincipal;
	
	public MenuConstrucciones(Juego j, InterfazPartida iPartida, InterfazPrincipal iPrincipal){	
		controladorJuego = j;
		jugadorActual = j.getJugadorActual();
		interfazPartida = iPartida;	
		interfazPrincipal = iPrincipal;
	}
	
	public void cargar() {
		JFrame frameConstruccion = new JFrame();
		frameConstruccion.getContentPane().removeAll();
		frameConstruccion.setJMenuBar(null);
		frameConstruccion.setTitle("Construccion");
		frameConstruccion.setSize(400, 400);
		
		JPanel panelConstruccion = new JPanel();
		for (int i = 0; i < jugadorActual.getRaza().getConstruccionesPosibles().size(); i++){
			Construccion auxiliar = jugadorActual.getRaza().getConstruccionesPosibles().get(i);
			panelConstruccion.add(generarBotonDeConstruccion(auxiliar));
		}
		
		frameConstruccion.getContentPane().add(panelConstruccion);
		frameConstruccion.setSize(700, 500);
		frameConstruccion.show();
	}
	
	public JButton generarBotonDeConstruccion(final Construccion aGenerar){
		JButton boton = new JButton(aGenerar.getNombre());
		final Construccion c = aGenerar;
		boton.addActionListener(new EscuchaBotonConstruccion(c));
		return boton;
	}
	
	private class CargarPosicion{
		int posX=0,posY=0;
		JFrame nuevaFrame;
		Construccion aConstruir;
		private CargarPosicion(Construccion c){		
			nuevaFrame = new JFrame();
			aConstruir = c;
		}
		
		private void cargarX(){			
			nuevaFrame.getContentPane().removeAll();
			nuevaFrame.setJMenuBar(null);
			nuevaFrame.setTitle("Pedido Posicion");
			nuevaFrame.setSize(300, 100);	
			JPanel panelPosicion = new JPanel();
			JLabel posicionX = new JLabel("Ingrese posicion X: ");
			JTextField ingresoPosX = new JTextField(3);
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
			JTextField ingresoPosY = new JTextField(3);
			ingresoPosY.setFocusable(true);
			ingresoPosY.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					posY = Integer.parseInt(ingresoPosY.getText());
					nuevaFrame.setVisible(false);
					try {				
						controladorJuego.ordenFabricacionDeEdificios(aConstruir, posX, posY);
						JOptionPane.showMessageDialog(null, "Construccion de " + aConstruir.getNombre() + " en camino!");
						interfazPartida.cargar(interfazPrincipal);
						
					} catch (ExcepcionNoPudoColocarseEdificio excepcion) {
						JOptionPane.showMessageDialog(null, excepcion.getMessage());
					}
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
	private class EscuchaBotonConstruccion implements ActionListener{
		Construccion aConstruir;
		public EscuchaBotonConstruccion(Construccion c) {
			try {
				aConstruir = c.getClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {				
			}
		}

		public void actionPerformed(ActionEvent arg0) {
			CargarPosicion pp = new CargarPosicion(aConstruir);					
			pp.cargarY();
			pp.cargarX();			
		}
	}
}
