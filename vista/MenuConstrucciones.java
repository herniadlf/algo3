package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionPosicionInvalida;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;

public class MenuConstrucciones {
	
	Jugador jugadorActual;
	InterfazPartida menuAnterior;
	ListaEdificiosEnPie listaEdificios;
	ListaDeRecursos listaDeRecursos;
	ListaDeEdificiosEnCurso listaEnCurso;
	JFrame frameConstruccion;
	InterfazPrincipal interfazPrincipal;
	
	public MenuConstrucciones(Jugador j, InterfazPartida interfazAnterior, InterfazPrincipal iPrincipal){	
		interfazPrincipal = iPrincipal;
		jugadorActual = j;
		menuAnterior = interfazAnterior;
		listaDeRecursos = new ListaDeRecursos(interfazAnterior);
		listaEdificios = new ListaEdificiosEnPie(interfazAnterior);
		listaEnCurso = new ListaDeEdificiosEnCurso(interfazAnterior);
		frameConstruccion = new JFrame();
	}
	
	public void cargar() {
		
		frameConstruccion.getContentPane().removeAll();
		frameConstruccion.setJMenuBar(null);
		frameConstruccion.setTitle("Construccion");		
		    
		JPanel panelConstruccion = new JPanel();
		
		String ruta = new String(System.getProperty("user.dir")+"\\trunk\\imagenes\\");
		ImageIcon imagenConstruccion = new ImageIcon(ruta+"imagenConstruccion.png");
		JLabel labelColor = new JLabel(imagenConstruccion);
		JLabel labelConstruccion = new JLabel(imagenConstruccion);
		labelColor.setBounds(0,0,imagenConstruccion.getIconWidth(),imagenConstruccion.getIconHeight());
		panelConstruccion.add(labelConstruccion);
		
		for (int i = 0; i < jugadorActual.getRaza().getConstruccionesPosibles().size(); i++){
			Construccion auxiliar = jugadorActual.getRaza().getConstruccionesPosibles().get(i);			
			panelConstruccion.add(generarBotonDeConstruccion(auxiliar));
		}
		JButton construcciones = new JButton("Construcciones en Pie");
		construcciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listaEdificios.cargar(interfazPrincipal);
			}
		});		
		JButton recursos = new JButton("Recursos Cercanos");
		recursos.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					listaDeRecursos.cargar(interfazPrincipal);
				} catch (ExcepcionPosicionInvalida e) {}
			}
		});	
		JButton enCurso = new JButton("Construcciones en Curso");
		enCurso.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listaEnCurso.cargar(interfazPrincipal);
				
			}
		});
		panelConstruccion.add(construcciones);
		panelConstruccion.add(recursos);
		panelConstruccion.add(enCurso);
		frameConstruccion.getContentPane().add(panelConstruccion);
		frameConstruccion.setSize(700, 500);
		frameConstruccion.setLocation(650,250);
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
						menuAnterior.getControlador().ordenFabricacionDeEdificios(aConstruir, posX, posY);
						JOptionPane.showMessageDialog(null, "Construccion de " + aConstruir.getNombre() + " en camino!");
						
					} catch (ExcepcionNoPudoColocarseEdificio excepcion) {
						JOptionPane.showMessageDialog(null, excepcion.getMessage());
					}
					limpiar();
					menuAnterior.cargar(interfazPrincipal);
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

	public void limpiar() {
		listaEdificios.limpiar();
		listaDeRecursos.limpiar();
		listaEnCurso.limpiar();
		frameConstruccion.getContentPane().removeAll();
		frameConstruccion.setJMenuBar(null);
		frameConstruccion.setVisible(false);
	}
}
