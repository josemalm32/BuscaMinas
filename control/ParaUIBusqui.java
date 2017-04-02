package control;

import modelo.Tablero;
import vista.UIbusqui;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class ParaUIBusqui extends UIbusqui {
	private JButton[][] botones;
	private Tablero tablero;
	private ReceptorEventos receptorEventos = new ReceptorEventos();

	public ParaUIBusqui() {
		crearBotones(20, 20);
		colocaMinas(50, 20, 20);
		receptorEventos.dameTodoLoco(tablero);
	}

	public void crearBotones(int ancho, int alto) {
		botones = new JButton[ancho][alto];
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

		// Copy y Paste de StackOverFlow jajaja
		int ancho1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto1 = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds((ancho1 / 2) - (this.getWidth() / 2), (alto1 / 2) - (this.getHeight() / 2), ancho * 40,
				alto * 40);

	}

	private void colocaMinas(int noMinas, int ancho, int alto) {
		Random rand = new Random();

		tablero = new Tablero(ancho, alto);
		int contador = 0;
		int x;
		int y;
		while (contador < noMinas) {
			x = rand.nextInt(ancho);
			y = rand.nextInt(alto);
			if (!tablero.getCasilla(x, y).isTieneMina()) {
				tablero.getCasilla(x, y).setTieneMina(true);
				contador++;
			}
		}

		for (int i = 1; i < ancho -1; i++) {
			for (int j = 1; j < ancho -1; j++) {
				if (tablero.getCasilla(i, j).isTieneMina()) {
					for (int k = -1; k <= 1; k++) {
						for (int l = -1; l <= 1; l++) {
							if (!tablero.getCasilla((i + k), (j + l)).isTieneMina()) {
								tablero.getCasilla((i + k), (j + l))
										.setNumero(tablero.getCasilla((i + k), (j + l)).getNumero() + 1);
							}
						}
					}
				}
			}
		}
	}

}
