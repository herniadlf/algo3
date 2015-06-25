package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;
import src.unidades.Golliat;
import src.unidades.Marine;
import src.unidades.NaveTransporteProtoss;
import src.unidades.Unidad;

public class MoverUnidad {
	

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
	
	
	
	public MoverUnidad(){
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
		
		JPanel panelSeleccionadorMOver = new JPanel();
		
		
		unidadesAlistadas.add(new Marine());
		unidadesAlistadas.add (new Golliat());
		unidadesAlistadas.add(new NaveTransporteProtoss());
		
		
		caragarListasDesplegablesUnidades (desplegableUnidadesAlistadas, unidadesIndexadas,
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
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	//------------------------------------------------------------------------------------------------------------

	public void caragarListasDesplegablesUnidades (JComboBox menuDesplegable, ArrayList<Unidad>unidadesIndexadas,
			ArrayList<Unidad> unidades){
		int i=0;
		while (unidades.size()>i){
			menuDesplegable.addItem(Integer.toString(i)+"."+ unidades.get(i).getNombre());
			unidadesIndexadas.add (unidades.get(i));
					i++;
				}
		}




	//------------------------------------------------------------------------------------------------	

	public void cargarNavesYPasajeros (){
		int i=0;
		while (unidadesAlistadas.size()>i){
			
		if (unidadesAlistadas.get(i).getNombre()== "Nave Transporte Terran"|| unidadesAlistadas.get(i).getNombre()== "Nave Transporte Protoss")
			{
			desplegableNaves.addItem(Integer.toString(i)+"."+unidadesAlistadas.get(i).getNombre());
			transporteIndexado.add(unidadesAlistadas.get(i));
			
			}else{ desplegableUnidadesTransporte.addItem(Integer.toString(i)+"."+unidadesAlistadas.get(i).getNombre());
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






	}
