package control;

import modelo.Tablero;
import vista.UIbusqui;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ParaUIBusqui extends UIbusqui {
	private JButton[][] botones;
	private Tablero tablero;
	private Varios varios = new Varios();

	public ParaUIBusqui() {
		crearBotones(50, 50);
		colocaMinas(200, 50, 50);
	}

	ActionListener receptoreventos = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton botonpulsado = (JButton) e.getSource();
			String[] posicionBoton = botonpulsado.getName().split(" ", 2);
			int x = Integer.parseInt(posicionBoton[0]);
			int y = Integer.parseInt(posicionBoton[1]);
			// Para Debug
			System.out.println(x);
			System.out.println(y);
			clickIzquierdo(x, y, false);

			// if(tablero.getCasilla(x, y).isTieneMina()){
			// botonpulsado.setText("MINAZA");
			// }else {
			// botonpulsado.setText(String.valueOf(tablero.getCasilla(x,
			// y).getNumero()));
			// }

		}
	};

	// Funcion Crear Botones..
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
				botones[i][j].addActionListener(receptoreventos);
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
				tablero.getCasilla(x, y).setNumero(-1);
				contador++;
			}
		}

		for (int i = 1; i < ancho - 1; i++) {
			for (int j = 1; j < alto - 1; j++) {
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

	public void clickIzquierdo(int x, int y, boolean recursivo) {
		if (!tablero.getCasilla(x, y).isVelada() && !tablero.getCasilla(x, y).isMarcada()) {
			tablero.getCasilla(x, y).setVelada(true);
			switch (tablero.getCasilla(x, y).getNumero()) {
			case -1:
				if (recursivo == false) {
					botones[x][y].setBackground(Color.RED);
					botones = varios.bloqueaBotones(botones);
					break;
				}
			case 0:
				botones[x][y].setBackground(Color.lightGray);
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						try {
							clickIzquierdo(x + i, y + j, true);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				break;
			default:
				botones[x][y].setText(Integer.toString(tablero.getCasilla(x, y).getNumero()));
				botones[x][y].setBackground(Color.lightGray);
				botones[x][y].setFont(new Font("Tahoma", Font.BOLD, 20));
				botones = varios.cambiaColorTexto(botones, x, y, tablero);
				break;
			}

		}
	}
}
