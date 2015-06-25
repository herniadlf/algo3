package vista;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;












import excepciones.ExcepcionElEdificioNoPerteneceATusConstrucciones;
import excepciones.ExcepcionNoHayConstruccionesCreadoras;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.unidades.Unidad;


public class CrearUnidades extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	Juego juego;
	Jugador jugador;
	LinkedList<Unidad> unidadesPosibles;
	ArrayList <Construccion> construcciones;
	String unidad;
	String construccion;
	JButton crear;
	private JLabel etiquetaUnidad;
	private JLabel  etiquetaEdificio;
	private ArrayList <Construccion> construccionesIndexadas;
	private ArrayList <Unidad> unidadesIndexadas;
	JComboBox desplegableUnidades;
	JComboBox desplegableEdificios;
	int indiceConstruccion;
	int indiceUnidad;
	MenuUnidades menuAnterior;
	
	
	public CrearUnidades(MenuUnidades menuUnidades){
	menuAnterior = menuUnidades;
	indiceConstruccion =0;
	indiceUnidad=0;
	unidad = new String();
	construccion = "";
	crear = new JButton();
	etiquetaUnidad= new JLabel();
	etiquetaEdificio= new JLabel(); 
	construccionesIndexadas = new ArrayList <Construccion>();
	unidadesIndexadas = new ArrayList <Unidad>();	
}	
		
	protected void cargar(final InterfazPrincipal ip) throws ExcepcionNoHayConstruccionesCreadoras  {
		
		JPanel panelCreacion = new JPanel();
		juego = ip.getJuego();
		jugador = ip.getJuego().getJugadorActual();
		unidadesPosibles = jugador.getRaza().getUnidadesPosibles();		
		preraraListaDeCreadoras();
		
		JComboBox desplegableUnidades = new JComboBox();
		desplegableUnidades.addItem("");
		JComboBox desplegableEdificios = new JComboBox();
		desplegableEdificios.addItem("");
		

		JFrame frameCrear= new JFrame();
		frameCrear.getContentPane().removeAll();
		frameCrear.setJMenuBar(null);
		frameCrear.setTitle("Creacion De Unidad");
		JPanel panelAtaque = new JPanel();
		
		int i =0;
		int j=0;
		
		
		cargarListaDesplegableConstrucciones (desplegableEdificios, construccionesIndexadas, construcciones);
		
		desplegableEdificios.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							construccion = event.getItem().toString();
							
					}
				}
			);
		
		
		while (unidadesPosibles.size()>i){
			desplegableUnidades.addItem(Integer.toString(i)+"."+ unidadesPosibles.get(i).getNombre());
			unidadesIndexadas.add (unidadesPosibles.get(i));
					i++;
				}
		
		desplegableUnidades.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidad= event.getItem().toString();
							
					}
				}
				);
		
		
	
		panelCreacion.add(desplegableUnidades);
		panelCreacion.add (desplegableEdificios);
		
		JButton crear = new JButton("Crear");
		crear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String indiceBuscadoConstruccion= "";
				String indiceBuscadoUnidades="";
				
				int posicionPuntoConstruccion=1;
				int posicionPuntoUnidades=1;				
				
				indiceConstruccion= obtenerIndiceDeElemento (posicionPuntoConstruccion, construccion, indiceBuscadoConstruccion);
				indiceUnidad= obtenerIndiceDeElemento (posicionPuntoUnidades, unidad, indiceBuscadoUnidades);					
					
				try {
					juego.ordenarFabricacionUnidad((unidadesIndexadas.get(indiceUnidad)).getClass().newInstance(), (Creadora) construccionesIndexadas.get(indiceConstruccion));
					JOptionPane.showMessageDialog(null, "Entrenamiento de unidad en camino!");
					frameCrear.getContentPane().removeAll();
					frameCrear.setJMenuBar(null);
					frameCrear.setVisible(false);
					menuAnterior.cargar(ip);
				} catch (ExcepcionNoPudoCrearseUnidad
						| ExcepcionElEdificioNoPerteneceATusConstrucciones | InstantiationException | IllegalAccessException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						frameCrear.getContentPane().removeAll();
						frameCrear.setJMenuBar(null);
						frameCrear.setVisible(false);
						menuAnterior.cargar(ip);
				}
	
					
					
					}
		});
		
		
		panelCreacion.add(crear);
		

		frameCrear.getContentPane().add(panelCreacion);
		frameCrear.setSize(700, 500);
		frameCrear.setLocation(650,250);
		frameCrear.show();
		
	
	
	
	}
	
	private void preraraListaDeCreadoras() throws ExcepcionNoHayConstruccionesCreadoras {
		construcciones = new ArrayList<Construccion>();
		int i= 0;
		while ( i < jugador.getConstruccionesEnPie().size()){
			Construccion auxiliar = jugador.getConstruccionesEnPie().get(i);
			if ( (auxiliar instanceof Creadora) ){
				construcciones.add(auxiliar);
			}
			i++;
		}
		if (construcciones.size() == 0){ throw new ExcepcionNoHayConstruccionesCreadoras("Construya algun edificio que cree unidades"); }
	}

	//------------------------------------------------------------------------------------------------	

	public void cargarListaDesplegableConstrucciones (JComboBox menuDesplegable, ArrayList<Construccion>construccionesIndexadas,
			ArrayList<Construccion> construccion){
		int i = 0;
		while ( i < construccion.size()){
			menuDesplegable.addItem(Integer.toString(i)+"."+ construccion.get(i).getNombre());
			construccionesIndexadas.add (construccion.get(i));
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





	
	
	
		
		



}
		






