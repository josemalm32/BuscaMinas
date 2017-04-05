package control;

import modelo.Imagenes;
import modelo.Sonido;
import modelo.Tablero;
import vista.UIbusqui;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class ParaUIBusqui extends UIbusqui {
	private JButton[][] botones;
	private Tablero tablero;
	private int marcadas;
	private int numeraDesveladas;
	static final int numeroMinas = 5;
	private Sonido sonido = new Sonido();
	static final int numeroCuadro = 50;
	static final int numeroADesvelar = (int) (Math.pow(50, 2) - numeroMinas);
	private Varios varios = new Varios();
	private Imagenes imagenes = new Imagenes();

	public ParaUIBusqui() {
		crearBotones(numeroCuadro, numeroCuadro);
		colocaMinas(numeroMinas, numeroCuadro, numeroCuadro);

	}

	MouseListener receptorbotonDerecha = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				JButton botonpulsado = (JButton) e.getSource();
				String[] posicionBoton = botonpulsado.getName().split(" ", 2);
				int x = Integer.parseInt(posicionBoton[0]);
				int y = Integer.parseInt(posicionBoton[1]);
				clickDerecho(botonpulsado, x, y);
			}
		}
	};
	ActionListener receptoreventos = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton botonpulsado = (JButton) e.getSource();
			String[] posicionBoton = botonpulsado.getName().split(" ", 2);
			int x = Integer.parseInt(posicionBoton[0]);
			int y = Integer.parseInt(posicionBoton[1]);
			clickIzquierdo(x, y, false);
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
				botones[i][j].setBackground(Color.BLUE);
				botones[i][j].setBorderPainted(true);
				botones[i][j].setName(i + " " + j);
				botones[i][j].addActionListener(receptoreventos);
				botones[i][j].addMouseListener(receptorbotonDerecha);
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
				//	botones = varios.bloqueaBotones(botones);
					// panelBotonera.setEnabled(false);
					sonido.ReproducirSonido("recursos/perder.wav");
					botones = varios.descubrirMinas(botones, tablero);
					break;
				}
			case 0:
				botones[x][y].setBackground(Color.white);
				
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						try {
							numeraDesveladas++;
							comprobarDesveladas();
							clickIzquierdo(x + i, y + j, true);
						} catch (Exception e) {

						}
					}
				}
				break;
			default:
				numeraDesveladas++;
				comprobarDesveladas();
				botones[x][y].setText(Integer.toString(tablero.getCasilla(x, y).getNumero()));
				botones[x][y].setBackground(Color.white);
				botones[x][y].setFont(new Font("Tahoma", Font.BOLD, 20));
				botones = varios.cambiaColorTexto(botones, x, y, tablero);
				break;
			}

		}
	}

	public void clickDerecho(JButton botonPulsado, int x, int y) {
		if (!tablero.getCasilla(x, y).isMarcada() && !tablero.getCasilla(x, y).isVelada()) {
			if (tablero.getCasilla(x, y).isTieneMina()) {
				marcadas++;
			}
			tablero.getCasilla(x, y).setMarcada(true);
			botonPulsado.setIcon(imagenes.getBandera());
		} else {
			tablero.getCasilla(x, y).setMarcada(false);
			if (tablero.getCasilla(x, y).isTieneMina()) {
				marcadas--;
				botonPulsado.setIcon(null);
			}
			botonPulsado.setText("");
		}
		if (marcadas == numeroMinas) {
			// TODO Poner ganador
		}
	}

	public void comprobarDesveladas(){
		if(numeraDesveladas == numeroADesvelar){


			
		}
	}

}


