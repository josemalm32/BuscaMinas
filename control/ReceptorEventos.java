package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ReceptorEventos implements ActionListener {
	
	ParaUIBusqui para;
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		   JButton botonpulsado = (JButton) arg0.getSource();
           String[] posicionBoton = botonpulsado.getName().split(" ", 2);
           int x = Integer.parseInt(posicionBoton[0]);
           int y = Integer.parseInt(posicionBoton[1]);
           //Para Debug
           System.out.println(x);
           System.out.println(y);
	}

}