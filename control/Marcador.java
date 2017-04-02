package control;

import javax.swing.JButton;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Tablero;

public class Marcador {

	public void marcarCasilla(JButton boton,Tablero tablero) {
		Coordenada coordenada=new Varios().obtenerCoordenada(boton);
		System.out.println(coordenada.getX() + coordenada.getY());
		Casilla casillaActual=tablero.getCasilla(coordenada);
		if(casillaActual.isVelada()){
			casillaActual.setMarcada(!casillaActual.isMarcada());
			if(casillaActual.isMarcada()){
				boton.setText("banderita");
			}
			else{
				boton.setText("");
			}
		}
	}
}
