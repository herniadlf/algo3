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
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSinNaves;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;
import src.unidades.DeTransporte;
import src.unidades.Unidad;

public class TransportarUnidad {

	Juego controladorJuego; 
	InterfazPartida menuAnterior;	
	InterfazPrincipal iPrincipal;
	JFrame frameTransportar;
	ArrayList<Unidad> listaNaves;
	ArrayList<Unidad> listaUnidades;
	
	public TransportarUnidad(InterfazPartida interfazAnterior) {
		// TODO Auto-generated constructor stub
		menuAnterior = interfazAnterior;	
		frameTransportar = new JFrame();
		listaNaves = new ArrayList<Unidad>();
		listaUnidades = new ArrayList<Unidad>();
	}

	public void cargar(InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		iPrincipal = ip;
		controladorJuego = ip.getJuego();
		listaUnidades = controladorJuego.getJugadorActual().getUnidadesAlistadas();
		
		try {
			cargarListaNaves(listaUnidades);
		} catch (ExcepcionSinNaves e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			frameTransportar.getContentPane().removeAll();
			frameTransportar.setJMenuBar(null);
			frameTransportar.setVisible(false);
			menuAnterior.cargar(ip);
		}
		
		frameTransportar.getContentPane().removeAll();
		frameTransportar.setJMenuBar(null);
		frameTransportar.setTitle("Seleccione unidad de transporte");
		JPanel unPanel = new JPanel();
		GridLayout gL = new GridLayout(listaNaves.size(),2);
		unPanel.setLayout(gL);
		for (int i = 0; i < listaNaves.size();i++){
			Unidad naveTransporte = listaNaves.get(i);
			JLabel unLabel = new JLabel(naveTransporte.getNombre() + "Posicion: (" + naveTransporte.getPosicionX() + "," + naveTransporte.getPosicionY() +") ");
			unPanel.add(unLabel);
			unPanel.add(generarBoton(naveTransporte));			
		}
		
		frameTransportar.getContentPane().add(unPanel);
		frameTransportar.pack();
		frameTransportar.setLocation(450,250);
		frameTransportar.show();
	}

	private void cargarListaNaves(ArrayList<Unidad> unidadesAlistadas) throws ExcepcionSinNaves {
		for (int i = 0; i < unidadesAlistadas.size(); i++){
			Unidad auxiliar = unidadesAlistadas.get(i);
			if (auxiliar instanceof DeTransporte){
				if (!listaNaves.contains(auxiliar)){
					listaNaves.add(auxiliar);
				}
			}
		}	
		if (listaNaves.size() == 0){
			throw new ExcepcionSinNaves("No tiene naves creadas");
		}
	}

	private JButton generarBoton(final Unidad nave) {
		
		JButton botonSeleccionar = new JButton("Seleccionar");
		botonSeleccionar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			limpiar();			
			cargarTropas((DeTransporte)nave);
			}
		});
		return botonSeleccionar;
	}

	protected void cargarTropas( final DeTransporte naveTransporte) {
		JPanel unPanel = new JPanel();
		GridLayout gL = new GridLayout(listaUnidades.size(), 2);
		unPanel.setLayout(gL);
		JLabel labelTransportar = new JLabel ("Una vez cargadas las unidades, haga click en el boton transportar\n");
		JButton botonTransportar = generarBotonTransportar(naveTransporte);

		unPanel.add(labelTransportar);
		unPanel.add(botonTransportar);
		
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		for (int i = 0; i < listaUnidades.size(); i++){
			final Unidad aTransportar = listaUnidades.get(i);
			if ( !(naveTransporte.getUnidadesAbordo().contains(aTransportar)) ){
				JLabel unLabel = new JLabel("\n" + aTransportar.getNombre() + " Posicion: (" + aTransportar.getPosicionX() + "," + 
														aTransportar.getPosicionY() +") ");
				
				JButton botonCargar = new JButton("Seleccionar");
				botonCargar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							naveTransporte.llevar(aTransportar);
							JOptionPane.showMessageDialog(null, "Unidad a bordo!");
								
						} catch (ExcepcionNoSePuedeTransportar
								| ExcepcionPosicionInvalida e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							limpiar();						
							cargarTropas(naveTransporte);
						}
					}
				});	
				unPanel.add(unLabel);
				unPanel.add(botonCargar);	
			}
		}
		
		frameTransportar.getContentPane().add(unPanel);
		frameTransportar.pack();
		frameTransportar.show();
	}

	private JButton generarBotonTransportar(DeTransporte naveTransporte) {
		JButton boton = new JButton("Transportar");
		boton.addActionListener(new EscuchaBotonSeleccionar(naveTransporte));
			return boton;
	}
	
	private class EscuchaBotonSeleccionar implements ActionListener{
		DeTransporte aTransportar;
		
		public EscuchaBotonSeleccionar(DeTransporte u) {			
			aTransportar = u;			
		}

		public void actionPerformed(ActionEvent arg0) {
			CargarPosicion pp = new CargarPosicion(aTransportar);					
			pp.cargarY();
			pp.cargarX();						
		}
	}
	
	private class CargarPosicion{
		int posX=0,posY=0;
		JFrame nuevaFrame;
		DeTransporte aTransportar;
		
		private CargarPosicion(DeTransporte nave){		
			nuevaFrame = new JFrame();
			aTransportar = nave;
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
						aTransportar.transportarUnidades(posX, posY);
						JOptionPane.showMessageDialog(null, "Transporte de " + aTransportar.getNombre() + " exitoso!");						
					} catch (ExcepcionNoPuedeMoverseUnidad
							| ExcepcionPosicionInvalida
							| ExcepcionNoHayLugarParaCrear
							| ExcepcionYaHayElementoEnLaPosicion e1) {	
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
	}

	public void limpiar() {
		frameTransportar.getContentPane().removeAll();
		frameTransportar.setJMenuBar(null);
		frameTransportar.setVisible(false);		
	}

}

