package control;

import java.awt.Color;

import javax.swing.JButton;

import modelo.Imagenes;
import modelo.Tablero;

public class Varios {
	private Imagenes imagenes = new Imagenes();

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
			botonera[x][y].setForeground(Color.RED);
			return botonera;
		case 2:
			botonera[x][y].setForeground(Color.green);
			return botonera;
		case 3:
			botonera[x][y].setForeground(Color.yellow);
			return botonera;
		case 4:
			botonera[x][y].setForeground(Color.pink);
			return botonera;
		case 5:
			botonera[x][y].setForeground(Color.orange);
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
				}
			}
		}
		return botonera;
	}
}
