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
	MenuUnidades menuAnterior;
	InterfazPartida iPartida;
	InterfazPrincipal iPrincipal;
	
	public MoverUnidad(MenuUnidades menuUnidades, InterfazPartida interfazPartida) {
		menuAnterior = menuUnidades;
		iPartida = interfazPartida;
	}

	public void cargar(InterfazPrincipal ip) {
		iPrincipal = ip;
		controladorJuego = ip.getJuego();
		ArrayList<Unidad> listaUnidades = controladorJuego.getJugadorActual().getUnidadesAlistades();
		
		JFrame frameMover = new JFrame();
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
		frameMover.setSize(700, 500);
		frameMover.setLocation(650,250);
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
							iPartida.cargar(iPrincipal);
						} catch (ExcepcionNoPuedeMoverseUnidad
								| ExcepcionLaUnidadNoPertenceATuTropa e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							iPartida.cargar(iPrincipal);
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
	
	
	
	
	
	
}	
	/*	

	private static final long serialVersionUID = 1L;
	Juego juego;
	Jugador jugador;
	
	int indiceUnidadAMover;
	
	ArrayList <Unidad> unidadesAlistadas;
	ArrayList<Unidad> unidadesIndexadas;
	ArrayList <Unidad> trasnportarIndexado;
	ArrayList <Unidad> transporteIndexado;
	String unidad;
	
	int posX ,posY;
	
	JButton moverUnidad;
	
	
	JComboBox desplegableUnidadesAlistadas;
	JComboBox desplegableNaves;
	JComboBox desplegableUnidadesTransporte;
	MenuUnidades menuAnterior;
	
	
	public MoverUnidad(MenuUnidades menuUnidades){
		menuAnterior = menuUnidades;
		unidadesAlistadas = new ArrayList<Unidad>();
		unidadesIndexadas = new ArrayList<Unidad>();
		trasnportarIndexado = new ArrayList <Unidad>();
		transporteIndexado = new ArrayList <Unidad>();
		desplegableUnidadesAlistadas = new JComboBox ();
		desplegableNaves = new JComboBox();
		desplegableUnidadesTransporte = new JComboBox();
		
		
		posX=0;
		 
		posY=0;
		
		
		}
	
	protected void cargar(final InterfazPrincipal ip){		
		
		jugador = ip.getJuego().getJugadorActual();
		juego= ip.getJuego();
		
		JFrame frameMover= new JFrame();
		frameMover.getContentPane().removeAll();
		frameMover.setJMenuBar(null);
		frameMover.setTitle("MoverUnidad");
		
		JPanel panelSeleccionadorMover = new JPanel();
		
		cargarListasDesplegablesUnidades (desplegableUnidadesAlistadas, unidadesIndexadas,
				unidadesAlistadas);

		desplegableUnidadesAlistadas.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidad= event.getItem().toString();						
					}
				}
				);
		
		cargarNavesYPasajeros ();
		
		
//--------------------------------------------------------------------------------------------------
		
		moverUnidad = new JButton("Mover Unidad");
		moverUnidad.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame framePanelMoverUnidad = new JFrame();
				framePanelMoverUnidad.getContentPane().removeAll();
				framePanelMoverUnidad.setJMenuBar(null);
				framePanelMoverUnidad.setTitle("Mover Unidad");	
				JPanel panelMoverUnidad= new JPanel();				

				String indiceBuscadoUnidadeAMover="";
				int posicionPuntoUnidadedAMover=0;
				
				panelMoverUnidad.add(desplegableUnidadesAlistadas);				
				indiceUnidadAMover= obtenerIndiceDeElemento(posicionPuntoUnidadedAMover, unidad, indiceBuscadoUnidadeAMover);				
				cargarX(framePanelMoverUnidad, unidadesIndexadas.get(indiceUnidadAMover) );	
			}		
			
		});
	
		panelSeleccionadorMover.add(moverUnidad);
		frameMover.getContentPane().add(panelSeleccionadorMover);
		frameMover.setSize(700, 500);
		frameMover.setLocation(650,250);
		frameMover.show();
	}
	
	
	
	//------------------------------------------------------------------------------------------------------------

	public void cargarListasDesplegablesUnidades (JComboBox menuDesplegable, ArrayList<Unidad>unidadesIndexadas,
			ArrayList<Unidad> unidades){
		unidades = juego.getJugadorActual().getUnidadesAlistades();
		int i=0;
		while (i < unidades.size()){
			menuDesplegable.addItem(Integer.toString(i)+"."+ unidades.get(i).getNombre());
			unidadesIndexadas.add (unidades.get(i));
					i++;
				}
		}




	//------------------------------------------------------------------------------------------------	

	public void cargarNavesYPasajeros (){
		int i=0;
		while (unidadesAlistadas.size()>i){
				
			if (unidadesAlistadas.get(i) instanceof DeTransporte){
				
				desplegableNaves.addItem(Integer.toString(i)+"."+unidadesAlistadas.get(i).getNombre());
				transporteIndexado.add(unidadesAlistadas.get(i));
				
				}
			else{ desplegableUnidadesTransporte.addItem(Integer.toString(i)+"."+unidadesAlistadas.get(i).getNombre());
			trasnportarIndexado.add(unidadesAlistadas.get(i));
		
			}
			
			i++;
		}		
	}

	//----------------------------------------------------------------------------------------------------

	public final  int obtenerIndiceDeElemento (int posicion, String cadena, String indiceBuscado){

		posicion= cadena.indexOf(".");
		int i=0;
		while (i < posicion){
			
			indiceBuscado= indiceBuscado+ (cadena.charAt(i));
			i++;
			}

		
		return (Integer.parseInt(indiceBuscado));
		
		}


	private void cargarX(final JFrame nuevaFrame, final Unidad unidad){			
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
				cargarY(nuevaFrame, unidad);
			}
		});		
		panelPosicion.add(posicionX);
		panelPosicion.add(ingresoPosX);				
		nuevaFrame.getContentPane().add(panelPosicion);
		nuevaFrame.show();
	}
	private void cargarY(final JFrame nuevaFrame, final Unidad unidad){
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
					
					jugador.moverUnidadAPosicion(unidad, posX, posY);
					
					
					//interfazPartida.cargar(interfazPrincipal);
					
				} catch (ExcepcionNoPuedeMoverseUnidad e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (ExcepcionLaUnidadNoPertenceATuTropa e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});		
		panelPosicion.add(posicionY);
		panelPosicion.add(ingresoPosY);				
		nuevaFrame.getContentPane().add(panelPosicion);
		nuevaFrame.show();
		
		frameAtacarConMagia.getContentPane().add(panelAtaqueConMagias);
		frameAtacarConMagia.setSize(700, 500);
		frameAtacarConMagia.setLocation(650,250);
		frameAtacarConMagia.show();
		
		
		
		
		
	}




*/

