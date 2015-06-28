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
import src.mapa.Posicion;
import src.unidades.AltoTemplario;
import src.unidades.Alucinacion;
import src.unidades.EMP;
import src.unidades.Magia;
import src.unidades.Magica;
import src.unidades.Marine;
import src.unidades.NaveCiencia;
import src.unidades.Radiacion;
import src.unidades.TormentaPsionica;
import src.unidades.Unidad;

	public class AtacarConMagia {
	
		private MetodosDeCarga metodosDeCarga;
		
		Juego juego;
		Jugador jugador;
		
		
		int indiceUnidadMagica;
		int indiceUnidadEnemiga;
		
		ArrayList<Unidad> unidadesMagicas;
		ArrayList <Unidad> unidadesEnemigas;
		
		String unidadEnemiga;
		String unidadMagica;
	
		
		
		JButton atacar;
		Magia magia;
	
		private ArrayList <Unidad> unidadesMagicasIndexadas;
		private ArrayList <Unidad> unidadesEnemigasIndexadas;
		
		JComboBox desplegableUnidadesMagicas;
		JComboBox desplegableUnidadesEnemigas;
		JComboBox desplegableMagiasPermitidas;
		
		JButton magia1;
		JButton magia2;
		
		
		JButton atacarUnidad;
		
		MenuUnidades menuAnterior;
		JFrame frameAtacarConMagia;
		
	public AtacarConMagia(MenuUnidades menuUnidades){
		
		metodosDeCarga = new MetodosDeCarga();
		menuAnterior = menuUnidades;
		unidadesMagicas= new ArrayList<Unidad>();
	
		unidadesEnemigas = new ArrayList <Unidad>();
		
		unidadMagica = new String();
		
		unidadEnemiga = new String ();
	
		
		unidadesMagicasIndexadas = new ArrayList<Unidad>();
		unidadesEnemigasIndexadas = new ArrayList <Unidad>();
	
		desplegableUnidadesMagicas= new JComboBox();
		desplegableUnidadesEnemigas = new JComboBox();
		desplegableMagiasPermitidas = new JComboBox();
		
		indiceUnidadMagica= 0;
		indiceUnidadEnemiga= 0;
		
		frameAtacarConMagia = new JFrame();
	}
	
	
	protected void cargar (final InterfazPrincipal ip){
		
		juego = ip.getJuego();
		jugador = ip.getJuego().getJugadorActual();
		
		if (jugador ==juego.getJugador1()){
			unidadesEnemigas= juego.getJugador2().getUnidadesAlistadas();
			
			}
		
		else {
			unidadesEnemigas= juego.getJugador1().getUnidadesAlistadas();
			
			}
					
		frameAtacarConMagia.getContentPane().removeAll();
		frameAtacarConMagia.setJMenuBar(null);
		frameAtacarConMagia.setTitle("AtaqueConMagia");
		JPanel panelAtaqueConMagias = new JPanel();
		
		
		desplegableUnidadesMagicas.addItem("");
		desplegableUnidadesEnemigas.addItem("");
		metodosDeCarga.cargarListaDesplegablesUnidadesMagicas( desplegableUnidadesMagicas, unidadesMagicas, jugador);
		metodosDeCarga.caragarListasDesplegablesUnidades (desplegableUnidadesEnemigas, unidadesEnemigasIndexadas,unidadesEnemigas);
		
				
		
	
		//___________________________________________________________________________________
		
		desplegableUnidadesMagicas.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidadMagica = event.getItem().toString();
							
					}
				}
			);
		
	
		desplegableUnidadesEnemigas.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidadEnemiga= event.getItem().toString();
							
					}
				}
				);
		
		
		
		panelAtaqueConMagias.add(desplegableUnidadesMagicas);
		panelAtaqueConMagias.add(desplegableUnidadesEnemigas);
		
		
		
		
	//_____________________________________________________________________________________________________________
	
		atacarUnidad = new JButton("Atacar Unidad Con Magia");
		atacarUnidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame framemagia = new JFrame();
				framemagia.getContentPane().removeAll();
				framemagia.setJMenuBar(null);
				framemagia.setTitle("Seleccione Magia");	
				JPanel panelDeMagias= new JPanel();
				
				
				String indiceBuscadoUnidadesMagicas="";
				int posicionPuntoUnidadesMagicas=0;
				
				
	
				String indiceBuscadoUnidadesEnemigas="";
				int posicionPuntoUnidadesEnemigas=0;
				
	
				
indiceUnidadEnemiga= metodosDeCarga.obtenerIndiceDeElemento(posicionPuntoUnidadesEnemigas, unidadEnemiga, indiceBuscadoUnidadesEnemigas);
indiceUnidadMagica= metodosDeCarga.obtenerIndiceDeElemento(posicionPuntoUnidadesMagicas, unidadMagica, indiceBuscadoUnidadesMagicas);
				
	
				crearBotonesMagias (indiceUnidadMagica);
				magia1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Magica agresor = (Magica) unidadesMagicas.get(indiceUnidadMagica);
						if (magia1.getActionCommand()=="Tormenta psionica"){
							
							magia = new TormentaPsionica (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));
							
						
							
							
						} else {
							
							magia = new EMP (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));
							
						
							
							
						}
						
						try {
							jugador.atacarCon(agresor, magia);
							JOptionPane.showMessageDialog(null, "Ataque exitoso con"+magia.obtenerNombre());
							System.out.print(magia);
							
						} catch (ExcepcionEdificioNoPuedeCrearUnidad
								| ExcepcionPosicionInvalida
								| ExcepcionNoHayLugarParaCrear
								| ExcepcionYaHayElementoEnLaPosicion
								| ExcepcionErrorPasoDeTurno
								| ExcepcionConstruccionNoCorrespondiente
								| ExcepcionRecursoInsuficiente
								| ExcepcionUnidadNoCorrespondiente
								| ExcepcionElementoFueraDelRangoDeAtaque
								| ExcepcionLaUnidadNoPertenceATuTropa e1) {						
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						menuAnterior.cargar(ip);
					}
				});
				
				
				magia2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Magica agresor = (Magica) unidadesMagicas.get(indiceUnidadMagica);
						if (magia2.getActionCommand()=="Alucinacion"){
							magia = new Alucinacion (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));
							
							
							
							
							
						
							
						} else {
						
							magia = new Radiacion (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));	
							
							
							
							
							
						}
						
						try {
							jugador.atacarCon(agresor, magia);
							JOptionPane.showMessageDialog(null, "Ataque exitoso con"+magia.obtenerNombre());
							System.out.print(magia);
						} catch (ExcepcionEdificioNoPuedeCrearUnidad
								| ExcepcionPosicionInvalida
								| ExcepcionNoHayLugarParaCrear
								| ExcepcionYaHayElementoEnLaPosicion
								| ExcepcionErrorPasoDeTurno
								| ExcepcionConstruccionNoCorrespondiente
								| ExcepcionRecursoInsuficiente
								| ExcepcionUnidadNoCorrespondiente
								| ExcepcionElementoFueraDelRangoDeAtaque
								| ExcepcionLaUnidadNoPertenceATuTropa e1) {						
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						menuAnterior.cargar(ip);
					}
				});
				
				
				
				
				
				
				
				
				
				
				
				
				
				panelDeMagias.add(magia1);
				panelDeMagias.add(magia2);
				
				framemagia.getContentPane().add(panelDeMagias);
				framemagia.setSize(700, 500);
				framemagia.setLocation(650,250);
				framemagia.show();
				
				
				
				}
			
			
			
		
		
	});
		
		
		panelAtaqueConMagias.add(atacarUnidad);
		frameAtacarConMagia.getContentPane().add(panelAtaqueConMagias);
		frameAtacarConMagia.setSize(700, 500);
		frameAtacarConMagia.setLocation(650,250);
		frameAtacarConMagia.show();
		
		
	
	//---------------------------------------------------------------------------------------------
	
	}  
	//aca terminar cargar
	
	public void crearBotonesMagias (int indiceUnidadMagica){
		if (unidadesMagicas.get(indiceUnidadMagica).getNombre()=="Alto Templario"){
				
				 magia1= new JButton ("Tormenta psionica");
				 magia2 = new JButton("Alucinacion");}
				
			
			else {
					 magia1= new JButton ("EMP");
					 magia2 = new JButton("Radiacion");
					
			}
	
	
	}
	
	
	public void limpiar() {
		frameAtacarConMagia.getContentPane().removeAll();
		frameAtacarConMagia.setJMenuBar(null);
		frameAtacarConMagia.setVisible(false);
	}
	

}
