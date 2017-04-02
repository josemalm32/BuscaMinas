package control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.Tablero;

public class ReceptorEventos implements ActionListener {
	
	Tablero tablero;
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {

           
	}
	
	
	public void dameTodoLoco(Tablero tableroEntrada) {
		tablero = tableroEntrada;
	}
	
	       
}