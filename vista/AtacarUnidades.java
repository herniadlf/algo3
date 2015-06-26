package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;
import src.unidades.Unidad;

	public class AtacarUnidades extends JFrame {
		
	
		private static final long serialVersionUID = 1L;
		Juego juego;
		Jugador jugador;
		
		ArrayList <Unidad> unidadesAlistadas;
		ArrayList <Construccion> construccionesEnemigas;
		ArrayList <Unidad> unidadesEnemigas;
		
		String unidadEnemiga;
		String unidadAlistada;
		String construccionEnemiga;
		
		
		JButton atacar;
		private ArrayList <Construccion> construccionesIndexadas;
		private ArrayList <Unidad> unidadesAlistadasIndexadas;
		private ArrayList <Unidad> unidadesEnemigasIndexadas;
		
		JComboBox desplegableUnidadesAlistadas;
		JComboBox desplegableEdificiosEnemigos;
		JComboBox desplegableUnidadesEnemigas;
		
		int indiceUnidadAlistadas;
		int indiceUnidadEnemigas;
		int indiceConstruccionesEnemigas;
		MenuUnidades menuAnterior;
		JFrame frameAtacar;
		
	public AtacarUnidades(MenuUnidades menuUnidades){
			menuAnterior = menuUnidades;
			unidadesAlistadas= new ArrayList<Unidad>();
			construccionesEnemigas = new ArrayList<Construccion>();
			unidadesEnemigas = new ArrayList <Unidad>();
			indiceUnidadAlistadas=0;
			indiceUnidadEnemigas =0;
			
			unidadAlistada = new String();
			construccionEnemiga = new String ();
			unidadEnemiga = new String ();
			
			construccionesIndexadas = new ArrayList<Construccion>();
			unidadesAlistadasIndexadas = new ArrayList<Unidad>();
			unidadesEnemigasIndexadas = new ArrayList <Unidad>();
			
			desplegableUnidadesAlistadas= new JComboBox();
			desplegableEdificiosEnemigos = new JComboBox();
			desplegableUnidadesEnemigas = new JComboBox();
			frameAtacar = new JFrame();
		}
		
		
	protected void cargar(final InterfazPrincipal ip)  {
			
		juego = ip.getJuego();
		jugador = ip.getJuego().getJugadorActual();
		
		unidadesAlistadas= jugador.getUnidadesAlistadas();
		
		if (jugador ==juego.getJugador1()){
			unidadesEnemigas= juego.getJugador2().getUnidadesAlistadas();
			construccionesEnemigas = juego.getJugador2().getConstruccionesEnPie();
			}
		
		else {
			unidadesEnemigas= juego.getJugador1().getUnidadesAlistadas();
			construccionesEnemigas = juego.getJugador1().getConstruccionesEnPie();
			}
		
		frameAtacar.getContentPane().removeAll();
		frameAtacar.setJMenuBar(null);
		frameAtacar.setTitle("Atacar Enemigo: Unidad   UnidadEnemiga   EdificioEnemigo");
		JPanel panelAtaque = new JPanel();
		
			desplegableUnidadesAlistadas.addItem("");
			desplegableEdificiosEnemigos.addItem("");
			desplegableUnidadesEnemigas.addItem("");
			
			caragarListasDesplegablesUnidades (desplegableUnidadesAlistadas, unidadesAlistadasIndexadas,unidadesAlistadas);
			
			desplegableUnidadesAlistadas.addItemListener(
					new ItemListener(){
						public void itemStateChanged(ItemEvent event){
							if(event.getStateChange()==ItemEvent.SELECTED)
								unidadAlistada = event.getItem().toString();
								
						}
					}
				);
			
			
			
			caragarListasDesplegablesUnidades (desplegableUnidadesEnemigas, unidadesEnemigasIndexadas,unidadesEnemigas);
			
			desplegableUnidadesEnemigas.addItemListener(
					new ItemListener(){
						public void itemStateChanged(ItemEvent event){
							if(event.getStateChange()==ItemEvent.SELECTED)
								unidadEnemiga= event.getItem().toString();
								
						}
					}
					);
			
			
			cargarListaDesplegableConstrucciones (desplegableEdificiosEnemigos, construccionesIndexadas, construccionesEnemigas);
			
			
			desplegableEdificiosEnemigos.addItemListener(
					new ItemListener(){
						public void itemStateChanged(ItemEvent event){
							if(event.getStateChange()==ItemEvent.SELECTED)
								construccionEnemiga= event.getItem().toString();
								
						}
					}
					);
			panelAtaque.add(desplegableUnidadesAlistadas);
			panelAtaque.add (desplegableUnidadesEnemigas);
			panelAtaque.add(desplegableEdificiosEnemigos);
			
	//-----------------------------------------------------------------------------------------------------
			
			JButton atacarUnidad = new JButton("AtacarUnidad");
			atacarUnidad.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String indiceBuscadoUnidadesAlistadas="";
					int posicionPuntoUnidadesAlistadas=1;
					
					
					String indiceBuscadoUnidadesEnemigas="";
					int posicionPuntoUnidadesEnemigas=1;
					
	
					
	
					indiceUnidadEnemigas = obtenerIndiceDeElemento (posicionPuntoUnidadesEnemigas, unidadEnemiga, indiceBuscadoUnidadesEnemigas);
					indiceUnidadAlistadas= obtenerIndiceDeElemento (posicionPuntoUnidadesAlistadas,unidadAlistada, indiceBuscadoUnidadesAlistadas);
					
					
						
					try {
						jugador.atacarCon( unidadesAlistadasIndexadas.get(indiceUnidadAlistadas),	unidadesEnemigasIndexadas.get(indiceUnidadEnemigas));
					} catch (ExcepcionEdificioNoPuedeCrearUnidad
							| ExcepcionPosicionInvalida
							| ExcepcionNoHayLugarParaCrear
							| ExcepcionYaHayElementoEnLaPosicion
							| ExcepcionErrorPasoDeTurno
							| ExcepcionElementoFueraDelRangoDeAtaque
							| ExcepcionLaUnidadNoPertenceATuTropa
							| ExcepcionConstruccionNoCorrespondiente
							| ExcepcionRecursoInsuficiente
							| ExcepcionUnidadNoCorrespondiente e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			});
			
		
		//-------------------------------------------------------------------------------------------------------
			
			JButton atacarEdificio = new JButton("AtacarEdificio");
			atacarEdificio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String indiceBuscadoUnidadesAlistadas="";
					int posicionPuntoUnidadesAlistadas=1;
					String indiceBuscadoConstruccionesEnemigas="";
					int posicionPuntoConstruccionesEnemigas=1;
					
	
					
	
					indiceConstruccionesEnemigas= obtenerIndiceDeElemento (posicionPuntoConstruccionesEnemigas, construccionEnemiga, indiceBuscadoConstruccionesEnemigas);	
					indiceUnidadAlistadas = obtenerIndiceDeElemento (posicionPuntoUnidadesAlistadas, unidadAlistada, indiceBuscadoUnidadesAlistadas);	
					
						
					try {
						jugador.atacarCon( unidadesAlistadasIndexadas.get(indiceUnidadAlistadas),	construccionesIndexadas.get(indiceConstruccionesEnemigas));
					} catch (ExcepcionEdificioNoPuedeCrearUnidad
							| ExcepcionPosicionInvalida
							| ExcepcionNoHayLugarParaCrear
							| ExcepcionYaHayElementoEnLaPosicion
							| ExcepcionErrorPasoDeTurno
							| ExcepcionElementoFueraDelRangoDeAtaque
							| ExcepcionLaUnidadNoPertenceATuTropa
							| ExcepcionConstruccionNoCorrespondiente
							| ExcepcionRecursoInsuficiente
							| ExcepcionUnidadNoCorrespondiente e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						
					}
		
							
						}
			});
			
			panelAtaque.add(atacarUnidad);
			panelAtaque.add (atacarEdificio);
	
			frameAtacar.getContentPane().add(panelAtaque);
			frameAtacar.setSize(700, 500);
			frameAtacar.setLocation(650,250);
			frameAtacar.show();
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
	
	public void cargarListaDesplegableConstrucciones (JComboBox menuDesplegable, ArrayList<Construccion>construccionesIndexadas,
			ArrayList<Construccion> construccion){
		int i=0;
		while (construccion.size()>i){
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
	
	
	public void limpiar() {
		frameAtacar.getContentPane().removeAll();
		frameAtacar.setJMenuBar(null);
		frameAtacar.setVisible(false);
	}
	
	
}
