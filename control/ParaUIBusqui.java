package control;

import modelo.Tablero;
import vista.UIbusqui;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.xml.bind.Marshaller.Listener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class ParaUIBusqui extends UIbusqui{
private JButton[][] botones;
private Tablero tablero;
private ReceptorEventos receptorEventos = new ReceptorEventos();
	public ParaUIBusqui() {
		crearBotones(20, 20);
		
	}
	
	

	
	public void crearBotones(int ancho,int alto) {
		botones = new JButton[ancho][alto];
		tablero = new Tablero(ancho,alto);
		panelBotonera.setLayout(new GridLayout(0, ancho, 0, 0));
	for (int i = 0; i < botones.length; i++) {
		for (int j = 0; j < botones[i].length; j++) {
			botones[i][j] = new JButton();
			botones[i][j].setPreferredSize(new Dimension(12, 12));
			botones[i][j].setBorder(new LineBorder(Color.BLACK));
			botones[i][j].setBorderPainted(true);
			botones[i][j].setName(i + " " + j);
			botones[i][j].addActionListener(receptorEventos);
			panelBotonera.add(botones[i][j]);
		}
	}	
	
	//Copy y Paste de StackOverFlow jajaja
	int ancho1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    this.setBounds((ancho1 / 2) - (this.getWidth() / 2), (alto1 / 2) - (this.getHeight() / 2), ancho * 40, alto *40);
		
			}
		
		
		   
	}


	
	
	
	
	
	

