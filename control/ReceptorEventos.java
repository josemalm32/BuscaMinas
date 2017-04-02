package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelo.Tablero;

public class ReceptorEventos implements ActionListener {
	
	Tablero tablero;
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		   JButton botonpulsado = (JButton) arg0.getSource();
           String[] posicionBoton = botonpulsado.getName().split(" ", 2);
           int x = Integer.parseInt(posicionBoton[0]);
           int y = Integer.parseInt(posicionBoton[1]);
           //Para Debug
           System.out.println(x);
           System.out.println(y);
           if(tablero.getCasilla(x, y).isTieneMina()){
        	   botonpulsado.setText("MINAZA");
           }else {
			botonpulsado.setText(String.valueOf(tablero.getCasilla(x, y).getNumero()));
		}
           
	}
	
	
	public void dameTodoLoco(Tablero tableroEntrada) {
		tablero = tableroEntrada;
	}

}