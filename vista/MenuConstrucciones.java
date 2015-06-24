package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import src.Jugador;
import src.construcciones.Construccion;

public class MenuConstrucciones {
	Jugador jugadorActual;
	InterfazPartida interfazPartida;
	public MenuConstrucciones(Jugador j, InterfazPartida ip){	
		jugadorActual = j;
		interfazPartida = ip;
	}
	
	public void cargar(final InterfazPrincipal ip) {
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		JPanel panelConstruccion = new JPanel();
		for (int i = 0; i < jugadorActual.getRaza().getConstruccionesPosibles().size(); i++){
			Construccion auxiliar = jugadorActual.getRaza().getConstruccionesPosibles().get(i);
			panelConstruccion.add(generarBotonDeConstruccion(auxiliar));
		}
		
		ip.getFramePrincipal().getContentPane().add(panelConstruccion);
		ip.getFramePrincipal().setSize(700, 500);
		ip.getFramePrincipal().show();
	}
	
	public JButton generarBotonDeConstruccion(final Construccion aGenerar){
		JButton boton = new JButton(aGenerar.getNombre());
		final Construccion c = aGenerar;
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			JFrame preguntaPosicion = new JFrame();
			preguntaPosicion.setVisible(true);
			JPanel contenedor = new JPanel();
			JLabel posX = new JLabel("Ingrese posicion X");
			final JTextField ingresoX = new JTextField(3);
			ingresoX.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					c.setPosicionX(Integer.parseInt(ingresoX.getText()));
					JFrame preguntaPosicion = new JFrame();
					preguntaPosicion.setVisible(true);
					JPanel contenedor = new JPanel();
					JLabel posY = new JLabel("Ingrese posicion Y");
					final JTextField ingresoY = new JTextField(3);
					c.setPosicionY(Integer.parseInt(ingresoY.getText()));
					contenedor.add(posY);
					contenedor.add(ingresoY);
					preguntaPosicion.add(contenedor);
				}
			});
			contenedor.add(posX);
			contenedor.add(ingresoX);
			preguntaPosicion.add(contenedor);
			try {
				interfazPartida.getControlador().ordenFabricacionDeEdificios(c.getClass().newInstance(), c.getPosicionX(), c.getPosicionY());
			} catch (ExcepcionNoPudoColocarseEdificio | InstantiationException | IllegalAccessException e1) {
				generarBotonDeConstruccion(aGenerar);
				}				
			}
		});
	return boton;
	}
}
