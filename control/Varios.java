package control;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import modelo.Imagenes;
import modelo.Tablero;



public class Varios {

	private Imagenes imagenes = new Imagenes();
	private Color verdeClaro = new Color(45, 255, 71);
	public JButton[][] bloqueaBotones(JButton[][] botonera) {

		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera[i].length; j++) {
				botonera[i][j].setEnabled(false);
			}
		}
		return botonera;
	}

	public JButton[][] cambiaColorTexto(JButton[][] botonera, int x, int y, Tablero tablero) {

		switch (tablero.getCasilla(x, y).getNumero()) {
		case 1:
			botonera[x][y].setForeground(Color.CYAN);
			return botonera;
		case 2:
			botonera[x][y].setForeground(Color.green);
			return botonera;
		case 3:
			botonera[x][y].setForeground(Color.yellow);
			return botonera;
		case 4:
			botonera[x][y].setForeground(Color.orange);
			return botonera;
		case 5:
			botonera[x][y].setForeground(Color.red);
			return botonera;
		}
		return botonera;

	}

	public JButton[][] descubrirMinas(JButton[][] botonera, Tablero tablero) {

		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera[i].length; j++) {
				if (tablero.getCasilla(i, j).isTieneMina()) {
					botonera[i][j].setIcon(imagenes.getMina());
					botonera[i][j].setBackground(Color.red);
				}else if(tablero.getCasilla(i, j).getNumero() != 0) {
					botonera[i][j].setFont(new Font("Tahoma", Font.BOLD, 20));
					botonera[i][j].setText(String.valueOf(tablero.getCasilla(i, j).getNumero()));
					botonera = cambiaColorTexto(botonera, i, j, tablero);
					botonera[i][j].setBackground(Color.white);
				}
				
			}
		}
		return botonera;
	}
}
