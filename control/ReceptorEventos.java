package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ReceptorEventos implements ActionListener {
	
	ParaUIBusqui para;
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//marcador.marcarCasilla((JButton) e.getSource(), tablero);
		Object eventSource = arg0.getSource();
       JButton clickedButton = (JButton) eventSource;
        String name = clickedButton.getName();

           String[] xy = clickedButton.getName().split(" ", 2);
           int x = Integer.parseInt(xy[0]);
           int y = Integer.parseInt(xy[1]);
           System.out.println(x);
           System.out.println(y);

//       

	}

}