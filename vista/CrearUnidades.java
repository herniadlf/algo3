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








import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionElEdificioNoPerteneceATusConstrucciones;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import src.Atacable;
import src.Juego;
import src.Jugador;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.mapa.Mapeable;
import src.razas.Protoss;
import src.razas.Raza;
import src.razas.Terran;
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
	
	
	public CrearUnidades(){
	unidad = new String();
	construccion = "10.afaf";
	crear = new JButton();
	etiquetaUnidad= new JLabel();
	etiquetaEdificio= new JLabel(); 
	construccionesIndexadas = new ArrayList <Construccion>();
	unidadesIndexadas = new ArrayList <Unidad>();
	
	
	
}
	
	
	
	
		
	protected void cargar(final InterfazPrincipal ip)  {
		
		JPanel panelCreacion = new JPanel();
		juego = ip.getJuego();
		jugador = ip.getJuego().getJugadorActual();
		unidadesPosibles = jugador.getRaza().getUnidadesPosibles();
		construcciones = jugador.getConstruccionesEnPie();
		construcciones.add(new Barraca());
		JComboBox desplegableUnidades = new JComboBox();
		desplegableUnidades.addItem("");
		JComboBox desplegableEdificios = new JComboBox();
		desplegableEdificios.addItem("");
		
				
				
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		ip.getFramePrincipal().setTitle("Elija primero la unidad y luego el edificio asociado");
		
		
		int i =0;
		int j=0;
		
		
		
		while (construcciones.size()>j){
			desplegableEdificios.addItem(Integer.toString(i)+"."+ construcciones.get(j).getNombre());
			construccionesIndexadas.add (construcciones.get(j));
			
			j++;
			}
		

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
				int indiceUnidad=0;
				int indiceConstruccion =0;
				
				
				
				posicionPuntoConstruccion = construccion.indexOf(".");
				
				int i=0;
				while (i < posicionPuntoConstruccion ){
					
					indiceBuscadoConstruccion= indiceBuscadoConstruccion+ (construccion.charAt(i));
					i++;
					}

					indiceConstruccion = Integer.parseInt(indiceBuscadoConstruccion);
				
					posicionPuntoUnidades= unidad.indexOf(".");
					
					
					
					int j=0;
					while (j < posicionPuntoUnidades){
						
						indiceBuscadoUnidades= indiceBuscadoUnidades+ (unidad.charAt(j));
						j++;
						}

						indiceUnidad = Integer.parseInt(indiceBuscadoUnidades);
					
				try {
					juego.ordenarFabricacionUnidad((unidadesIndexadas.get(indiceUnidad)), (Creadora) construccionesIndexadas.get(indiceConstruccion));
				} catch (ExcepcionNoPudoCrearseUnidad
						| ExcepcionElEdificioNoPerteneceATusConstrucciones e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
					
					
					
						
					
					
				
			
			}
		});
		
		
		panelCreacion.add(crear);
		
		ip.getFramePrincipal().getContentPane().add(panelCreacion,BorderLayout.CENTER);
		ip.getFramePrincipal().setSize(400, 100);
		ip.getFramePrincipal().show();
		
	
	
	
	}
		
		



}
		






